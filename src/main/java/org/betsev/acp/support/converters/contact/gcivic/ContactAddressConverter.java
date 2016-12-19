package org.betsev.acp.support.converters.contact.gcivic;

import org.betsev.acp.business.contact.entity.Address;
import org.betsev.acp.support.Constants;
import org.dozer.ConfigurableCustomConverter;

import java.util.List;

/**
 * Created by sevburmaka on 12/7/16.
 */
public class ContactAddressConverter implements ConfigurableCustomConverter{
    String parameter ;

    @Override
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        List<Address> addresses = (List<Address>) sourceFieldValue;

        for (Address address : addresses){
            if (isCorrectAddress(address))
                return address;
        }

        return null;
    }

    private boolean isCorrectAddress(Address address){
        if (parameter.equals(Constants.TYPE_DC) && address.getState().equals(Constants.DC_STATE_CODE)){
            return true;
        }
        if (parameter.equals(Constants.TYPE_DISTRICT) && !address.getState().equals(Constants.DC_STATE_CODE)){
            return true;
        }
        return false;
    }
}
