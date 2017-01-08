package org.betsev.acp.business.contact.control.uscl;

import org.betsev.acp.business.contact.control.ContactUnifierService;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.uscl.USCLContact;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Service("usclContactUnifierService")
public class USCLContactUnifierService implements ContactUnifierService {
    private static final Logger LOG = LoggerFactory.getLogger(USCLContactUnifierService.class);

    @Autowired
    USCLContactRepository contactRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    @Override
    public Contact getCorrespondingContact(Contact other) {
        USCLContact contact= contactRepository.getCorrespondingContact(other);
        if (contact == null){
            return null;
        }

        return beanMapper.map(contact,Contact.class);
    }
}
