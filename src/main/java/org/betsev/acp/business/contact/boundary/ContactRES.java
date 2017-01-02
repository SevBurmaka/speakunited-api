package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sevburmaka on 11/30/16.
 */
@RestController
@RequestMapping("/contacts")
public class ContactRES {

    @Autowired
    ContactService contactService;

    @Autowired
    ContactUnifierGateway contactUnifierGateway;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> get(@RequestParam(required = false) String address,@RequestParam(required = false) String type) throws Exception {
        List<Contact> rawContacts;

        if (StringUtils.isEmpty(address))
            rawContacts = contactService.getAllContacts();
        else {
            ContactType contactType = ContactType.getByValue(type);
            rawContacts = contactService.findByAddressAndType(address, contactType);
        }
        return unifyContacts(rawContacts);
    }

    private List<Contact> unifyContacts(List<Contact> contacts){

        List<Contact> unified = contacts.stream().map(
                contactUnifierGateway::getContactInfo
        ).collect(Collectors.toList());

        return unified;
    }
}
