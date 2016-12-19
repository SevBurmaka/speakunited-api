package org.betsev.acp.support.converters.contact.gcivic;

import org.dozer.ConfigurableCustomConverter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
public class ContactWebsiteConverter implements ConfigurableCustomConverter {
    String parameter ;

    @Override
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        List<String> urls= (List<String>) sourceFieldValue;

        //@Todo might need to double check this in case there actually are multiple URLs
        if (!CollectionUtils.isEmpty(urls)){
            return urls.get(0);
        }

        return null;
    }

}

