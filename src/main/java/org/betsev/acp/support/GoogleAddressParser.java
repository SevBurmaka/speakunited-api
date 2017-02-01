package org.betsev.acp.support;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import org.betsev.acp.business.contact.entity.Address;
import org.betsev.acp.config.ApiKeyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by sevburmaka on 1/29/17.
 */
@Service
public class GoogleAddressParser implements AddressParser {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleAddressParser.class);

    @Autowired
    ApiKeyConfig apiKeyConfig;

    private GeoApiContext context;

    @PostConstruct
    void init (){
        context = new GeoApiContext().setApiKey(apiKeyConfig.getGoogleApiKey());
    }

    @Override
    public Address parseAddress(String address) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context,
                    address).await();
            AddressComponent[] components = results[0].addressComponents;
            Address parsedAddress = new Address();
            String streetNumber = "";
            String street= "";

            for (int x = 0; x < components.length ; x++){
                AddressComponent curComponent = components[x];
                AddressComponentType type = curComponent.types[0];
                if (type.equals(AddressComponentType.STREET_NUMBER))
                        streetNumber = curComponent.longName;
                else if (type.equals(AddressComponentType.SUBPREMISE))
                    parsedAddress.setLine2(curComponent.longName);
                else if (type.equals(AddressComponentType.ROUTE))
                        street = curComponent.shortName;
                else if (type.equals(AddressComponentType.LOCALITY)) //@TODO add in address line 2
                    parsedAddress.setCity(curComponent.longName);
                else if (type.equals(AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1))
                    parsedAddress.setState(curComponent.shortName);
                else if (type.equals(AddressComponentType.POSTAL_CODE))
                    parsedAddress.setZip(curComponent.longName);
            }

            parsedAddress.setLine1(streetNumber+" "+street);
            return parsedAddress;

        }catch (Exception e){
            LOG.error("Error parsing address: {} ",address,e);
        }
        return null;
    }
}
