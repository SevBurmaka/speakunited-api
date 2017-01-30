package org.betsev.acp.business.letter.boundary;

import com.lob.client.AsyncLobClient;
import com.lob.client.LobClient;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.response.LetterResponse;
import org.betsev.acp.business.contact.boundary.ContactService;
import org.betsev.acp.business.contact.entity.Address;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.letter.entity.LetterRequest;
import org.betsev.acp.business.letter.entity.PaidLetterRequest;
import org.betsev.acp.config.ApiKeyConfig;
import org.betsev.acp.support.LetterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;

/**
 * Created by sevburmaka on 1/29/17.
 */
public class LobLetterService implements LetterService {
    private static final Logger LOG = LoggerFactory.getLogger(LobLetterService.class);
    @Autowired
    ApiKeyConfig apiKeyConfig;

    @Autowired
    @Qualifier("unified")
    ContactService contactService;

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

        final LobClient client = AsyncLobClient.createDefault("test_0dc8d51e0acffcb1880e0f19c79b2f5b0cc");

        File letterFile = LetterUtil.createHtmlFile(letter);
        final com.lob.protocol.request.LetterRequest letterRequest = com.lob.protocol.request.LetterRequest.builder()
                .to(createAddressRequest(contact.getName(),address))
                .file(letterFile)
                .color(true)
                .build();

        try {
            final LetterResponse letterResponse = client.createLetter(letterRequest).get();
            LOG.info("Generated letter: {}", letterResponse);
        }
        catch (final Exception e) {
            LOG.error("Failed Letter {}", letter,e);
            return false;
        }
        finally{
            LetterUtil.deleteOnAfterDelay(letterFile);
        }
        return true;
    }

    private AddressRequest createAddressRequest(String repName, Address address){
        return AddressRequest.builder()
                .name(repName)
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country("US")
                .build();
    }

    public boolean doPayment(PaidLetterRequest request){
        //@todo check payment information exists
        return true;
    }

}
