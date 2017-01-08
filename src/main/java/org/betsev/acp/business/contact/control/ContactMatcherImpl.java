package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.BaseContact;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.support.NameMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sevburmaka on 1/7/17.
 */
@Service
public class ContactMatcherImpl implements ContactMatcher{
    private static final Logger LOG = LoggerFactory.getLogger(ContactMatcherImpl.class);

    @Autowired
    NameMatcher nameMatcher;

    @Override
    public BaseContact findMatchingContact(List<BaseContact> contacts, BaseContact toFind) {
        Optional<BaseContact> byId = contacts.stream().filter(it-> {
            if (it.getBioguide() == null || toFind.getBioguide() == null) return false;
            return it.getBioguide().equals(toFind.getBioguide());
        }).findFirst();
        if (byId.isPresent())
            return byId.get();

        List<String> names = contacts.stream().map(
                it-> it.getFullName()
        ).collect(Collectors.toList());

        int bestMatch = nameMatcher.getBestMatchIndex(toFind.getFullName(),names,0.9);
        //for now just matching on full name
        if (bestMatch < 0){
            LOG.error("Could not find corresponding contact for {}",toFind.getFullName());
            return new Contact();
        }
        return contacts.get(bestMatch);
    }
}
