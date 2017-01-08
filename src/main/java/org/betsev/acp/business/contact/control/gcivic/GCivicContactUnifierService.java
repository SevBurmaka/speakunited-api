package org.betsev.acp.business.contact.control.gcivic;

import org.betsev.acp.business.contact.control.ContactUnifierService;
import org.betsev.acp.business.contact.entity.Contact;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sevburmaka on 1/7/17.
 */
@Service("gcivicContactUnifierService")
public class GCivicContactUnifierService implements ContactUnifierService {
    private static final Logger LOG = LoggerFactory.getLogger(GCivicContactUnifierService.class);

    @Autowired
    GCivicContactRepository contactRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    @Override
    public Contact getCorrespondingContact(Contact other) {
        Contact contact= contactRepository.getCorrespondingContact(other);
        if (contact == null){
            return null;
        }

        return contact;
    }
}
