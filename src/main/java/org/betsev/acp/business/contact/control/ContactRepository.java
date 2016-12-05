package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.Contact;

import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
public interface ContactRepository {
    List<Contact> getAll();
    List<Contact> findByAddressAndType(String address, String type);
}
