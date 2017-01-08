package org.betsev.acp.business.contact.control;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.support.ContactCopyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Component
public class ContactUnifierAggregator {
    private static final Logger LOG = LoggerFactory.getLogger(ContactUnifierAggregator.class);

    public Contact unify(List<Contact> contacts){
        Contact unified = new Contact();
        BeanUtilsBean notNull=new ContactCopyUtilsBean();
        contacts.forEach(it->{
            if (it != null) {
                try {
                    notNull.copyProperties(unified, it);
                } catch (Exception e) {
                    LOG.error("Exception during bean property copying: ", e);
                }
            }
        });
        return unified;
    }
}
