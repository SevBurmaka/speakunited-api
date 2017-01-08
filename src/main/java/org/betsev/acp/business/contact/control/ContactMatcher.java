package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.BaseContact;

import java.util.List;

/**
 * Created by sevburmaka on 1/7/17.
 */
public interface ContactMatcher {
     BaseContact findMatchingContact(List<BaseContact> contacts, BaseContact toFind);
}
