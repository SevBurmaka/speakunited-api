package org.betsev.acp.business.letter.boundary;

import org.betsev.acp.business.letter.entity.LetterRequest;

/**
 * Created by sevburmaka on 1/29/17.
 */
public interface LetterService {
    //create the html file from the request. don't forget to delete it once it's been used!
    boolean sendLetter(LetterRequest letter);

}
