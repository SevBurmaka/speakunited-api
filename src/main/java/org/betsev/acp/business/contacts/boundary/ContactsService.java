package org.betsev.acp.business.contacts.boundary;

import org.betsev.acp.business.contacts.control.ContactRepository;
import org.betsev.acp.business.contacts.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Service
public class ContactsService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.getAll();
    }
}
