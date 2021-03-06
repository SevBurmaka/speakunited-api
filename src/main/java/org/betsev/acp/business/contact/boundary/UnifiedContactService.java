package org.betsev.acp.business.contact.boundary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.betsev.acp.business.contact.control.ContactRepository;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;
import org.betsev.acp.business.contact.entity.IdInfo;
import org.betsev.acp.business.contact.entity.gcivic.GCivicContact;
import org.betsev.acp.business.contact.entity.gcivic.TypeMapping;
import org.betsev.acp.config.ApiKeyConfig;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Service
@Qualifier("unified")
public class UnifiedContactService implements ContactService {
    private static final Logger LOG = LoggerFactory.getLogger(UnifiedContactService.class);

    @Autowired
    @Qualifier("unified")
    ContactRepository contactRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    @Autowired
    ApiKeyConfig apiKeyConfig;

    private static String GCIVIC_URL = "https://www.googleapis.com/civicinfo/v2/representatives";

    public List<Contact> getAllContacts(){
        return contactRepository.getAll();
    }

    @Override
    public List<Contact> findByAddressAndType(String address, ContactType type) {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(GCIVIC_URL);

        webTarget =
                webTarget.queryParam("levels", "country")
                        .queryParam("address", address)
                        .queryParam("roles", TypeMapping.get(type))
                        .queryParam("key", apiKeyConfig.getGoogleApiKey());

        return queryApi(webTarget,type);
    }

    private List<Contact> queryApi(WebTarget webTarget, ContactType type){
        Response response = webTarget.request().buildGet().invoke();
        String jsonString = response.readEntity(String.class);
        JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();

        //find the relevant field and marshal that
        JsonElement officials = jsonObject.get("officials");
        Gson gson = new Gson();
        GCivicContact[] officialContacts = gson.fromJson(officials, GCivicContact[].class);

        ArrayList<Contact> contacts = new ArrayList<>();

        for (GCivicContact gcontact : officialContacts){
            Contact contact = beanMapper.map(gcontact,Contact.class);
            contact.setType(type.getDisplayValue());
            //need to get the unified versino of this contact
            contacts.add(contactRepository.getCorrespondingContact(contact));
        }

        return contacts;
    }

    @Override
    public List<Contact> findByDistrictAndType(String district, ContactType type) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget webTarget = client.target(GCIVIC_URL + "/" + URLEncoder.encode(district, "UTF-8"));

            webTarget =
                    webTarget.queryParam("levels", "country")
                            .queryParam("roles", TypeMapping.get(type))
                            .queryParam("recursive", true)
                            .queryParam("key", apiKeyConfig.getGoogleApiKey());

            return queryApi(webTarget, type);
        }catch(UnsupportedEncodingException e){
            LOG.error("Unsupported encoding: ",e);
        }
        return null;
    }

    @Override
    public Contact getByBioguideId(String bioguideId) {
        Contact toFind = new Contact();
        IdInfo idInfo = new IdInfo();
        idInfo.setBioguide(bioguideId);
        toFind.setIdInfo(idInfo);

        return contactRepository.getCorrespondingContact(toFind);
    }
}
