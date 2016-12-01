package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@RestController
@RequestMapping("/contacts")
public class ContactRES {

    @Autowired
    ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> get() throws Exception {
        List<Contact> contacts = contactService.getAllContacts();

        return contacts;
    }
}
