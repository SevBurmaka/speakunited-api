package org.betsev.acp.business.contact.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class USCLContact {

    IdInfo id;
    USCLName name;

}

