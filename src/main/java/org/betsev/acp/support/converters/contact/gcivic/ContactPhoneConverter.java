package org.betsev.acp.support.converters.contact.gcivic;


import org.betsev.acp.support.Constants;
import org.dozer.ConfigurableCustomConverter;

import java.util.List;

/**
 * Created by sevburmaka on 12/7/16.
 */
public class ContactPhoneConverter implements ConfigurableCustomConverter {
    String parameter ;

    @Override
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        List<String> phones = (List<String>) sourceFieldValue;

        if (phones != null) {
            for (String phone : phones) {
                if (isCorrectPhone(phone))
                    return phone;
            }
        }

        return null;
    }

    private boolean isCorrectPhone(String phone){
        if (parameter.equals(Constants.TYPE_DC) && phone.replace("(","").startsWith(Constants.DC_ZIP)){
            return true;
        }
        if (parameter.equals(Constants.TYPE_DISTRICT) && !phone.replace("(","").startsWith(Constants.DC_ZIP)){
            return true;
        }
        return false;
    }
}
