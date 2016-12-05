package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
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
    ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> get(@RequestParam(required = false) String address,@RequestParam(required = false) String type) throws Exception {
        if (StringUtils.isEmpty(address))
            return contactService.getAllContacts();
        else
            return contactService.findByAddressAndType(address,type);
    }
}
