package org.betsev.acp.business.letter.boundary;

import org.betsev.acp.business.letter.entity.PaidLetterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sevburmaka on 1/29/17.
 */
@RestController
@RequestMapping("/api/letter")
public class LetterRES {
    private static final Logger LOG = LoggerFactory.getLogger(org.betsev.acp.business.letter.boundary.LetterRES.class);

    @Autowired
    LetterService letterService;

    //todo send more meaningful responses
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> sendFax(@RequestBody PaidLetterRequest letterRequest){
        LOG.info("Processing letter request: {}",letterRequest);
        boolean success = letterService.sendLetter(letterRequest);
        if (success)
            return ResponseEntity.ok("Success");
        else
            return ResponseEntity.badRequest().body(null);
    }
}
