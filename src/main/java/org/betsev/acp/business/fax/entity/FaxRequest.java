package org.betsev.acp.business.fax.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sevburmaka on 1/11/17.
 */
@Getter
@Setter
public class FaxRequest {
    String bioguideId;
    String body;
    String header;
    String name;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String zip;

    @Override
    public String toString() {
        return "FaxRequest{" +
                "bioguideId='" + bioguideId + '\'' +
                ", body='" + body + '\'' +
                ", header='" + header + '\'' +
                ", name='" + name + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
