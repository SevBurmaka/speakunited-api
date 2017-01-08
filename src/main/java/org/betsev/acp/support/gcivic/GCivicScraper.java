package org.betsev.acp.support.gcivic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.betsev.acp.ArmchairPoliticsApp;
import org.betsev.acp.business.contact.boundary.ContactService;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Populate gcivic master file
 */
@SpringBootApplication
@ComponentScan
public class GCivicScraper {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ArmchairPoliticsApp.class, args);
        ContactService contactService = (ContactService) ctx.getBean("contactServiceImpl");

        try {
            InputStream in = new ClassPathResource("open-civic-districts.csv").getInputStream();
            Scanner scanner = new Scanner(in);

            Set<String> divisions = new HashSet<>();

            while (scanner.hasNext()) {
                String ocd = scanner.next();
                Pattern p = Pattern.compile("(ocd-division/country:us/state:[a-z][a-z].*),");
                Matcher m = p.matcher(ocd);
                if (m.find()) {
                    divisions.add(m.group(1));
                }
            }

            List<Contact> allContacts = new ArrayList<>();

//            doQueries(divisions, contactService, allContacts,ContactType.SENATE);
            doQueries(divisions, contactService, allContacts,ContactType.HOUSE);

            //need to manually query OH and PA because they break when querying by district
            allContacts.addAll(contactService.findByAddressAndType("1 Capitol Square, Columbus, OH 43215",ContactType.SENATE));
            allContacts.addAll(contactService.findByAddressAndType("Commonwealth Avenue Harrisburg, Pennsylvania",ContactType.SENATE));
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            File out = new File("gcivic-contacts.yaml");
            mapper.writeValue(out, allContacts.toArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    static Set<String> doQueries(Set<String> divisions, ContactService contactService, List<Contact> allContacts, ContactType contactType) {
        if (divisions.isEmpty()) return null;
        else
            return doQueries(
                    divisions.stream().filter(division -> doQuery(division, contactService, allContacts, contactType))
                            .collect(Collectors.toSet())
                    , contactService, allContacts,contactType);

    }


    static boolean doQuery(String division, ContactService contactService, List<Contact> allContacts, ContactType contactType) {
        System.out.println("Querying division : " + division);
        if (!(division.startsWith("ocd-division/country:us/state:pa") || division.startsWith("ocd-division/country:us/state:oh")))
            return false;
        try {
            Thread.sleep(10000);
            List<Contact> contacts= contactService.findByDistrictAndType(division, contactType);
            allContacts.addAll(contacts);
            return false;
        } catch (Exception e) {
            System.out.println("Exception while querying division: " + division);
            e.printStackTrace();
            return true;
        }
    }
}