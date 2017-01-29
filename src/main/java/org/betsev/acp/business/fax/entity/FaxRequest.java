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
    String address;
    @Override
    public String toString() {
        return "FaxRequest{" +
                "bioguideId='" + bioguideId + '\'' +
                ", body='" + body + '\'' +
                ", header='" + header + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
