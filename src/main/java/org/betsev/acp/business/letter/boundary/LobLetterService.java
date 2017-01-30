package org.betsev.acp.business.letter.boundary;

import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.id.AddressId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.LetterResponse;
import org.betsev.acp.business.contact.boundary.ContactService;
import org.betsev.acp.business.contact.entity.Address;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.letter.entity.LetterRequest;
import org.betsev.acp.business.letter.entity.PaidLetterRequest;
import org.betsev.acp.config.ApiKeyConfig;
import org.betsev.acp.support.AddressParser;
import org.betsev.acp.support.LetterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * Created by sevburmaka on 1/29/17.
 */
@Service
public class LobLetterService implements LetterService {
    private static final Logger LOG = LoggerFactory.getLogger(LobLetterService.class);
    @Autowired
    ApiKeyConfig apiKeyConfig;

    @Autowired
    @Qualifier("unified")
    ContactService contactService;

    @Autowired
    AddressParser addressParser;

    @Override
    public boolean sendLetter(LetterRequest letter) {
        PaidLetterRequest paidLetterRequest = (PaidLetterRequest)letter;
        if (!doPayment(paidLetterRequest))
            return false;
        Contact contact = contactService.getByBioguideId(letter.getBioguideId());
        Address address;
        //@todo need to have a field in the request to decide which of these we are contact
        if (contact.getDcAddress() != null)
            address = contact.getDcAddress();
        else if (contact.getDistrictAddress() != null)
            address = contact.getDistrictAddress();
        else {
            LOG.error("No addresses found for bioguide: {}",letter.getBioguideId());
            return false;
        }
        String letterHtml = LetterUtil.createHtmlString(letter);

        try {
            final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

            final com.lob.protocol.request.LetterRequest letterRequest = com.lob.protocol.request.LetterRequest.builder()
                    .to(createAddressRequest(contact.getName(),address,client))
                    .from(createAddressRequest(paidLetterRequest.getName(),paidLetterRequest.getAddress(),client))
                    .file(letterHtml)
                    .color(false)
                    .build();


            final LetterResponse letterResponse = client.createLetter(letterRequest).get();
            LOG.info("Generated letter: {}", letterResponse);
        }
        catch (final Exception e) {
            LOG.error("Failed Letter {}", letter,e);
            return false;
        }
        return true;
    }

    private AddressId createAddressRequest(String name, Address address, LobClient client) throws InterruptedException,ExecutionException{
        final AddressRequest request =  AddressRequest.builder()
                .name(name)
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country("US")
                .build();

        final AddressResponse exampleAddressResponse = client.createAddress(request).get();

        return exampleAddressResponse.getId();
    }

    private AddressId createAddressRequest(String name, String address, LobClient client) throws InterruptedException,ExecutionException{
        return createAddressRequest(name,addressParser.parseAddress(address),client);
    }

    public boolean doPayment(PaidLetterRequest request){
        //@todo check payment information exists
        return true;
    }

}
