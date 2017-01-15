package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@RestController
@RequestMapping("/contacts")
public class ContactRES {

    @Autowired
    @Qualifier("unified")
    ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> get(@RequestParam(required = false) String address,@RequestParam(required = false) String type) throws Exception {
        List<Contact> contacts;

        if (StringUtils.isEmpty(address))
            contacts = contactService.getAllContacts();
        else {
            ContactType contactType = ContactType.getByValue(type);
            contacts = contactService.findByAddressAndType(address, contactType);
        }
        return contacts;
    }

}
