package org.betsev.acp.business.contact.control.uscl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.betsev.acp.business.contact.control.ContactMatcher;
import org.betsev.acp.business.contact.entity.BaseContact;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.uscl.USCLContact;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by sevburmaka on 12/11/16.
 */
@Service
public class USCLContactRepository  {
    private static final Logger LOG = LoggerFactory.getLogger(USCLContactRepository.class);

    private List<USCLContact> contacts;

    @Autowired
    ContactMatcher contactMatcher;

    @Autowired
    DozerBeanMapper beanMapper;

    @PostConstruct
    private void init(){
        try {
            contacts = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream contactStream = new ClassPathResource("uscl/legislators-current.yaml").getInputStream();
            USCLContact[] contactsArray = new USCLContact[0];
            contacts = CollectionUtils.arrayToList(mapper.readValue(contactStream,contactsArray.getClass()));

            //need to get the social media handles separately and then unify them
            List<USCLContact> socialMediaContacts = new ArrayList<>();
            InputStream socialMediaContactStream = new ClassPathResource("uscl/legislators-social-media.yaml").getInputStream();
            USCLContact[] socialMediaContactsArray = new USCLContact[0];
            socialMediaContacts = CollectionUtils.arrayToList(mapper.readValue(socialMediaContactStream,socialMediaContactsArray.getClass()));

            socialMediaContacts.stream().forEach(social -> {
                int index = contacts.indexOf(social);
                if (index == -1)
                    LOG.error("Did not find matching USCL contact from social media contact: {}",social);
                else{
                   contacts.get(index).setSocialMedia(social.getSocialMedia());
                }

            });

            if (contacts.isEmpty())
                LOG.error("Did not read any legislators from USCL data");
        }catch(IOException e){
            LOG.error("Error reading legislator contacts file ",e);
        }
    }

    public List<USCLContact> getAll() {
        return contacts;
    }

    public USCLContact getCorrespondingContact(Contact other) {
        return (USCLContact) contactMatcher.findMatchingContact(
                contacts.stream().map(it-> (BaseContact) it).collect(Collectors.toList()),
                other);
    }
}
