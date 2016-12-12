package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.business.contact.entity.Contact;
import org.springframework.integration.annotation.Gateway;

/**
 * Created by sevburmaka on 12/11/16.
 */
public interface ContactUnifierGateway {

    @Gateway(requestChannel="contactsChannel")
    Contact getContactInfo(Contact contact);
}

