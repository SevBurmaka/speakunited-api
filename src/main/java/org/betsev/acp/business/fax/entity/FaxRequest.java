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

    @Override
    public String toString() {
        return "FaxRequest{" +
                "bioguideId='" + bioguideId + '\'' +
                '}';
    }
}
