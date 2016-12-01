package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Component
public class FileContactRepository implements ContactRepository {
    @Override
    public List<Contact> getAll() {
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact test = new Contact();
        test.setName("Mimi Walters");
        contacts.add(test);
        return contacts;
    }
}
