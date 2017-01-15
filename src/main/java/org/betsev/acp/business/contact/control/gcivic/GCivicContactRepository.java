package org.betsev.acp.business.contact.control.gcivic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.betsev.acp.business.contact.control.ContactMatcher;
import org.betsev.acp.business.contact.control.uscl.USCLContactRepository;
import org.betsev.acp.business.contact.entity.BaseContact;
import org.betsev.acp.business.contact.entity.Contact;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sevburmaka on 1/7/17.
 */
@Service
@Qualifier("gcivic")
public class GCivicContactRepository {
    private static final Logger LOG = LoggerFactory.getLogger(USCLContactRepository.class);

    private List<Contact> contacts;

    @Autowired
    ContactMatcher contactMatcher;

    @Autowired
    DozerBeanMapper beanMapper;

    @PostConstruct
    private void init(){
        try {
            contacts = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream contactStream = new ClassPathResource("gcivic/gcivic-contacts.yaml").getInputStream();
            Contact[] contactsArray = new Contact[0];
            contacts = CollectionUtils.arrayToList(mapper.readValue(contactStream,contactsArray.getClass()));

            if (contacts.isEmpty())
                LOG.error("Did not read any legislators from USCL data");
        }catch(IOException e){
            LOG.error("Error reading legislator contacts file ",e);
        }
    }

    public List<Contact> getAll() {
        return contacts;
    }

    public Contact getCorrespondingContact(Contact other) {
       return (Contact) contactMatcher.findMatchingContact(
                contacts.stream().map(it-> (BaseContact) it).collect(Collectors.toList()),
                other);
    }
}
