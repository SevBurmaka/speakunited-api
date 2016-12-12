package org.betsev.acp.business.contact.control;

import com.google.gson.Gson;
import org.betsev.acp.business.contact.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Component
public class FileContactRepository implements ContactRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FileContactRepository.class);
    @Override
    public List<Contact> getAll() {
        ArrayList<Contact> contacts = new ArrayList<>();

        Gson gson = new Gson();
        try {
            InputStream senateContacts = new ClassPathResource("contacts/senators.json").getInputStream();
            BufferedReader senateReader = new BufferedReader(new InputStreamReader(senateContacts));
            contacts = gson.fromJson(senateReader, contacts.getClass());

        }catch(IOException e){
            LOG.error("Error while reading senate contacts: {}",e);
        }

        return contacts;
    }

    @Override
    public Contact getCorrespondingContact(Contact other) {
        return null;
    }


}
