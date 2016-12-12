package org.betsev.acp.business.contact.control;

import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.USCLContact;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Service("usclContactUnifierService")
public class USCLContactUnifierService implements ContactUnifierService {
    @Autowired
    USCLContactRepository contactRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    @Override
    public Contact getCorrespondingContact(Contact other) {
        USCLContact contact= contactRepository.getCorrespondingContact(other);

        return beanMapper.map(contact,Contact.class);
    }
}
