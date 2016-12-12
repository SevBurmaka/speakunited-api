package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Component
public class ContactUnifierAggregator {
    public Contact unify(List<Contact> contacts){
        //@todo actually unify
        return contacts.get(0);
    }
}
