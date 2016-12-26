package org.betsev.acp.business.contact.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sevburmaka on 12/25/16.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocialMedia {
    String facebook;
    @JsonProperty("facebook_id")
    String facebookId;
    String twitter;
    @JsonProperty("twitter_id")
    String twitterId;
    String youtube;
    @JsonProperty("youtube_id")
    String youtubeId;
    String instagram;
    @JsonProperty("instagram_id")
    String instagramId;

}
