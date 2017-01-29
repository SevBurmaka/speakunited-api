package org.betsev.acp.business.letter.entity;

/**
 * Created by sevburmaka on 1/29/17.
 */
public class LetterRequest {
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
