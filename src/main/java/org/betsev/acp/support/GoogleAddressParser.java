package org.betsev.acp.support;

import org.betsev.acp.business.contact.entity.Address;
import org.betsev.acp.config.ApiKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sevburmaka on 1/29/17.
 */
@Service
public class GoogleAddressParser implements AddressParser {
    @Autowired
    ApiKeyConfig apiKeyConfig;

    private static String GOOGLE_URL = "https://www.googleapis.com/civicinfo/v2/representatives";



    @Override
    public Address parseAddress(String address) {
        return null;
    }
}
