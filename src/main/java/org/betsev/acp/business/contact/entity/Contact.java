package org.betsev.acp.business.contact.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Getter
@Setter
public class Contact {
    String name;

    String firstName;

    String lastName;

    String state;

    String party;

    String website;

    IdInfo idInfo;

    String photoUrl;

    String emailContactForm;

    String emailAddress;

    Address districtAddress;

    String districtPhone;

    String districtFax;

    Address dcAddress;

    String dcPhone;

    String dcFax;

    String district;

    String type;

    SocialMedia socialMedia;

    @Override
    public String toString() {
        return "Contact{" +
                "idInfo=" + idInfo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
