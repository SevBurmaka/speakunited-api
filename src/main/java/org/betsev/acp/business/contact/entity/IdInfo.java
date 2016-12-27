package org.betsev.acp.business.contact.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by sevburmaka on 12/17/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdInfo {
    String bioguide;
    String lis;
    String thomas;
    String govtrack;
    String opensecrets;
    String votesmart;
    List<String> fec;
    String cspan;
    String wikipedia;
    @JsonProperty("house_history")
    String houseHistory;
    String ballotpedia;
    String maplight;
    String icpsr;
    String wikidata;
    @JsonProperty("google_entity_id")
    String googleEntityId;

    @Override
    public String toString() {
        return "IdInfo{" +
                "bioguide='" + bioguide + '\'' +
                ", lis='" + lis + '\'' +
                ", thomas='" + thomas + '\'' +
                ", govtrack='" + govtrack + '\'' +
                ", opensecrets='" + opensecrets + '\'' +
                ", votesmart='" + votesmart + '\'' +
                ", fec=" + fec +
                ", cspan='" + cspan + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", houseHistory='" + houseHistory + '\'' +
                ", ballotpedia='" + ballotpedia + '\'' +
                ", maplight='" + maplight + '\'' +
                ", icpsr='" + icpsr + '\'' +
                ", wikidata='" + wikidata + '\'' +
                ", googleEntityId='" + googleEntityId + '\'' +
                '}';
    }
}
