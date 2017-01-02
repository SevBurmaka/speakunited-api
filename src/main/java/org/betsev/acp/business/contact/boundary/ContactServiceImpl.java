package org.betsev.acp.business.contact.boundary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.betsev.acp.ArmchairPoliticsApp;
import org.betsev.acp.business.contact.control.USCLContactRepository;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.ContactType;
import org.betsev.acp.business.contact.entity.GCivic.GCivicContact;
import org.betsev.acp.business.contact.entity.GCivic.TypeMapping;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    USCLContactRepository contactRepository;

    @Autowired
    DozerBeanMapper beanMapper;

    public List<Contact> getAllContacts(){
        return contactRepository.getAll().stream().map(it ->
                beanMapper.map(it,Contact.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Contact> findByAddressAndType(String address, ContactType type) {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("https://www.googleapis.com/civicinfo/v2/representatives");

        webTarget =
                webTarget.queryParam("levels", "country")
                        .queryParam("address", address)
                        .queryParam("roles", TypeMapping.get(type))
                        .queryParam("key", ArmchairPoliticsApp.GOOGLE_API_KEY);

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
            contacts.add(contact);
        }

        return contacts;
    }
}
