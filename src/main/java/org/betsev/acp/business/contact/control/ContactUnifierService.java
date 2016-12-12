package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.Contact;

/**
 * Created by sevburmaka on 12/11/16.
 */
public interface ContactUnifierService {
    Contact getCorrespondingContact(Contact other);

}
