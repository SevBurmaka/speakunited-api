package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.USCLContact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Service
public class USCLContactRepository  {
    public List<Contact> getAll() {
        return null;
    }

    public USCLContact getCorrespondingContact(Contact other) {
        USCLContact contact = new USCLContact();
        contact.setBioguideId("BIOGUIDE_ID");
        return contact;
    }
}
