package org.betsev.acp.support;

import org.betsev.acp.SpeakUnitedApp;
import org.betsev.acp.business.contact.boundary.ContactService;
import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 Scrape congressman websites for fax numbers
 */
@SpringBootApplication
@ComponentScan
public class FaxScraper {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpeakUnitedApp.class, args);
        ContactService contactService = (ContactService) ctx.getBean("unifiedContactService");
        try {
            List<Contact> contacts = contactService.getAllContacts();
            contacts.stream().forEach(it -> {
                String url = it.getWebsite();
                if (!StringUtils.isEmpty(url)){

                }
            });
//            InputStream in = new ClassPathResource("open-civic-districts.csv").getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
