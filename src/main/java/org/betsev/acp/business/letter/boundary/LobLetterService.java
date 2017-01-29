package org.betsev.acp.business.letter.boundary;

import org.betsev.acp.business.contact.boundary.ContactService;
import org.betsev.acp.business.letter.entity.LetterRequest;
import org.betsev.acp.business.letter.entity.PaidLetterRequest;
import org.betsev.acp.config.ApiKeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by sevburmaka on 1/29/17.
 */
public class LobLetterService implements LetterService {
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

        return true;
    }

    public boolean doPayment(PaidLetterRequest request){
        //@todo check payment information exists
        return true;
    }

}
