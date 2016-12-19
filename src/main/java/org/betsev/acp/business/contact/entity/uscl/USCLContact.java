package org.betsev.acp.business.contact.entity.uscl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.betsev.acp.business.contact.entity.IdInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class USCLContact {

    IdInfo id;
    USCLName name;
    USCLBio bio;
    List<USCLTerm> terms;

    public USCLTerm getLastTerm(){
        if (!CollectionUtils.isEmpty(terms))
            return terms.get(terms.size()-1);
        else return null;
    }
}

