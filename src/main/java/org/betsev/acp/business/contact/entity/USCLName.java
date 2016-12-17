package org.betsev.acp.business.contact.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sevburmaka on 12/17/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class USCLName {
        String first;
        String last;
        @JsonProperty("official_full")
        String full;
}
