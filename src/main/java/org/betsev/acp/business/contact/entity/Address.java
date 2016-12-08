package org.betsev.acp.business.contact.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sevburmaka on 12/4/16.
 */
@Getter
@Setter
public class Address {
    public final static String TYPE_DISTRICT = "district";
    public final static String TYPE_DC = "dc";

    String line1;
    String line2;
    String line3;
    String city;
    String state;
    String zip;
}
