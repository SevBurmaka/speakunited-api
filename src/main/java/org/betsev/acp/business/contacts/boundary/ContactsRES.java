package org.betsev.acp.business.contacts.boundary;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by sevburmaka on 11/30/16.
 */
@RestController
@RequestMapping("/contacts")
public class ContactsRES {

    @RequestMapping(method = RequestMethod.GET)
    public ArrayList get() throws Exception {
        ArrayList<String> contacts = new ArrayList<>();
        contacts.add("A contact");

        return contacts;
    }
}
