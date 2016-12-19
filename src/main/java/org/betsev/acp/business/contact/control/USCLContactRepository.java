package org.betsev.acp.business.contact.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.uscl.USCLContact;
import org.betsev.acp.support.NameMatcher;
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
    NameMatcher nameMatcher;

    @PostConstruct
    private void init(){
        try {
            contacts = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            InputStream contactStream = new ClassPathResource("uscl/legislators-current.yaml").getInputStream();
            USCLContact[] contactsArray = new USCLContact[0];
            contacts = CollectionUtils.arrayToList(mapper.readValue(contactStream,contactsArray.getClass()));
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
        List<String> names = contacts.stream().map(
                it-> it.getName().getFull()
        ).collect(Collectors.toList());

        int bestMatch = nameMatcher.getBestMatchIndex(other.getName(),names,0.9);
        //for now just matching on full name
        if (bestMatch < 0){
            LOG.error("Could not find corresponding contact in USCL repo for {}",other.getName());
            return null;
        }
        return contacts.get(bestMatch);
    }
}
