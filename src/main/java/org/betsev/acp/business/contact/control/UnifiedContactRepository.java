package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.boundary.ContactUnifierGateway;
import org.betsev.acp.business.contact.entity.BaseContact;
import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sevburmaka on 1/14/17.
 */
@Service
@Qualifier("unified")
public class UnifiedContactRepository implements ContactRepository {

    List<Contact> contacts = new ArrayList<>();
    @Autowired
    ContactMatcher contactMatcher;

    @Autowired
    ContactUnifierGateway contactUnifierGateway;

    public void unifyContacts(List<Contact> contacts){
        this.contacts = contacts.stream().map(
                contactUnifierGateway::getContactInfo
        ).collect(Collectors.toList());
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public Contact getCorrespondingContact(Contact other) {
        return (Contact) contactMatcher.findMatchingContact(
                contacts.stream().map(it-> (BaseContact) it).collect(Collectors.toList()),
                other);
    }
}
