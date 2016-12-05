package org.betsev.acp.business.contact.boundary;

import org.betsev.acp.ArmchairPoliticsApp;
import org.betsev.acp.business.contact.control.ContactRepository;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.contact.entity.TypeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sevburmaka on 11/30/16.
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.getAll();
    }

    @Override
    public List<Contact> findByAddressAndType(String address, String type) {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("https://www.googleapis.com/civicinfo/v2/representatives");
//        WebTarget resourceWebTarget = webTarget.path("resource");
//        WebTarget helloworldWebTarget = resourceWebTarget.path("helloworld");

        webTarget =
                webTarget.queryParam("levels", "country")
                        .queryParam("address", address)
                        .queryParam("roles", TypeMapping.get(type))
                        .queryParam("key", ArmchairPoliticsApp.API_KEY);


        Response response = webTarget.request().buildGet().invoke();
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));

        return contactRepository.findByAddressAndType(address,type);
    }
}
