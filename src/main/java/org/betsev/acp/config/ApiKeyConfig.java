package org.betsev.acp.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by sevburmaka on 1/14/17.
 */
@Setter
@Getter
@Component
public class ApiKeyConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ApiKeyConfig.class);

    @Value("${google-api-key-location}")
    String googleApiKeyLocation;
    @Value("${phaxio-api-key-location}")
    String phaxioApiKeyLocation;
    @Value("${lob-api-key-location}")
    String lobApiKeyLocation;
    @Value("${stripe-api-key-location}")
    String stripeApiKeyLocation;

    String googleApiKey;
    String phaxioApiKey;
    String phaxioApiSecret;
    String lobApiKey;
    String stripeApiKey;

    @PostConstruct
    public void init(){
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            if (!StringUtils.isEmpty(googleApiKeyLocation))
                googleApiKey = mapper.readTree(new File(googleApiKeyLocation)).get("key").asText();
            if (!StringUtils.isEmpty(lobApiKeyLocation))
                lobApiKey = mapper.readTree(new File(lobApiKeyLocation)).get("key").asText();
            if (!StringUtils.isEmpty(phaxioApiKeyLocation)) {
                JsonNode node = mapper.readTree(new File(phaxioApiKeyLocation));
                phaxioApiKey = node.get("key").asText();
                phaxioApiSecret = node.get("secret").asText();
            }
            if (!StringUtils.isEmpty(stripeApiKeyLocation))
                stripeApiKey = mapper.readTree(new File(stripeApiKeyLocation)).get("key").asText();

        }catch (Exception e){
            LOG.error("Error while getting api key: ",e);
        }
    }


}
