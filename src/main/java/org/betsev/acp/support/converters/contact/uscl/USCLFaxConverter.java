package org.betsev.acp.support.converters.contact.uscl;

import org.betsev.acp.business.contact.entity.uscl.USCLTerm;
import org.dozer.ConfigurableCustomConverter;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Sometimes the fax info from USCL is in not in the latest term, but can use an earlier term to find it
 */
public class USCLFaxConverter implements ConfigurableCustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        List<USCLTerm> terms = (List<USCLTerm>) sourceFieldValue;

        String fax = getFax(terms.get(terms.size()-1));
        if (StringUtils.isEmpty(fax) && terms.size() > 1)
            fax = getFax(terms.get(terms.size()-2));

        return fax;
    }

    private String getFax(USCLTerm term){
        if (!StringUtils.isEmpty(term.getFax()))
            return term.getFax();
        return null;
    }

    @Override
    public void setParameter(String parameter) {

    }
}
