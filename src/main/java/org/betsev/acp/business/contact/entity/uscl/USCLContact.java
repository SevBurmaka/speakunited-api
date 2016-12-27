package org.betsev.acp.business.contact.entity.uscl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.betsev.acp.business.contact.entity.IdInfo;
import org.betsev.acp.business.contact.entity.SocialMedia;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class USCLContact implements Comparable {

    IdInfo id;
    USCLName name;
    USCLBio bio;
    List<USCLTerm> terms;
    @JsonProperty("social")
    SocialMedia socialMedia;

    public USCLTerm getLastTerm(){
        if (!CollectionUtils.isEmpty(terms))
            return terms.get(terms.size()-1);
        else return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        USCLContact that = (USCLContact) o;

        return (id.getBioguide().equals(((USCLContact) o).getId().getBioguide()));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return -1;
        if (o == null || getClass() != o.getClass()) return -1;

        USCLContact that = (USCLContact) o;

        return (id.getBioguide().compareTo(((USCLContact) o).getId().getBioguide()));
    }

    @Override
    public String toString() {
        return "USCLContact{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

