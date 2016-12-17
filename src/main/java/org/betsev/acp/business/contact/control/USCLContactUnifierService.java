package org.betsev.acp.business.contact.control;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.USCLContact;
import org.betsev.acp.support.NullAwareBeanUtilsBean;
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

        Contact mapped = beanMapper.map(contact,Contact.class);
        try {
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(mapped, other);
        }catch (Exception e){
            LOG.error("Exception during bean property copying: ",e);
        }

        return mapped;
    }
}
