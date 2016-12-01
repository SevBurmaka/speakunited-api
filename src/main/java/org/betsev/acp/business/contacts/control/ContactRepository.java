package org.betsev.acp.business.contacts.control;

import org.betsev.acp.business.contacts.entity.Contact;

import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
public interface ContactRepository {
    List<Contact> getAll();
}
