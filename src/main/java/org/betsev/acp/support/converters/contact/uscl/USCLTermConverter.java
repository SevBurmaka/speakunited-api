package org.betsev.acp.support.converters.contact.uscl;

import org.betsev.acp.business.contact.entity.uscl.USCLTerm;
import org.dozer.ConfigurableCustomConverter;

import java.util.List;

/**
 * Created by sevburmaka on 12/18/16.
 */
public abstract class USCLTermConverter implements ConfigurableCustomConverter {
    String parameter ;

    @Override
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public abstract Object convertField(USCLTerm source);

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        List<USCLTerm> terms = (List<USCLTerm>)sourceFieldValue;
        return convertField(terms.get(terms.size()-1));
    }

}
