package org.betsev.acp.business.contact.entity.uscl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by sevburmaka on 12/18/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class USCLTerm {
    String type;
    Date start;
    Date end;
    String state;
    String district;
    String party;
    String url;
    String address;
    String phone;
    String fax;
    @JsonProperty("contact_form")
    String contactForm;
    String office;
    @JsonProperty("rss_url")
    String rssUrl;
    @JsonProperty("state_rank")
    String stateRank;
}
