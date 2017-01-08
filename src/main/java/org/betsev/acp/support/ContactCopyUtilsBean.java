package org.betsev.acp.support;

/**
 * Created by sevburmaka on 12/17/16.
 */

import org.apache.commons.beanutils.BeanUtilsBean;
import org.betsev.acp.business.contact.entity.Address;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.IdInfo;
import org.betsev.acp.business.contact.entity.SocialMedia;

import java.lang.reflect.InvocationTargetException;

public class ContactCopyUtilsBean extends BeanUtilsBean{

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)return;
        BeanUtilsBean notNull=new ContactCopyUtilsBean();

        if (name.equals("socialMedia")){
            SocialMedia socialMedia = ((Contact)dest).getSocialMedia();
            if (socialMedia == null) socialMedia = new SocialMedia();
            notNull.copyProperties(socialMedia,value);
            ((Contact)dest).setSocialMedia(socialMedia);
        }
        else if (name.equals("districtAddress")){
            Address address = ((Contact)dest).getDistrictAddress();
            if (address == null) address = new Address();
            notNull.copyProperties(address,value);
            ((Contact)dest).setDistrictAddress(address);
        }
        else if (name.equals("dcAddress")){
            Address address = ((Contact)dest).getDcAddress();
            if (address == null) address = new Address();
            notNull.copyProperties(address,value);
            ((Contact)dest).setDcAddress(address);
        }
        else if (name.equals("idInfo")){
            IdInfo idInfo = ((Contact)dest).getIdInfo();
            if (idInfo == null) idInfo = new IdInfo();
            notNull.copyProperties(idInfo,value);
            ((Contact)dest).setIdInfo(idInfo);
        }
        else super.copyProperty(dest, name, value);
    }

}