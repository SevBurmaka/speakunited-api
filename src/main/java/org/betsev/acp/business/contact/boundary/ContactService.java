package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.control.ContactRepository;
import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.getAll();
    }
}
