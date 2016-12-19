package org.betsev.acp.business.contact.entity.uscl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by sevburmaka on 12/18/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class USCLBio {
    Date birthday;
    String gender;
    String religion;
}
