package org.betsev.acp.business.contacts.control;

import org.betsev.acp.business.contacts.entity.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
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
