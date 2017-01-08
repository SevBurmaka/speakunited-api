package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;

import java.util.List;

/**
 * Created by sevburmaka on 12/4/16.
 */
public interface ContactService {
    List<Contact> getAllContacts();
    List<Contact> findByAddressAndType(String address, ContactType type);
    List<Contact> findByDistrictAndType(String district, ContactType type);
}
