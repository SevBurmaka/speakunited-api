package org.betsev.acp.business.fax.boundary;

import org.betsev.acp.business.fax.entity.FaxRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sevburmaka on 1/11/17.
 */
@RestController
@RequestMapping("/api/fax")
public class FaxRES {
    private static final Logger LOG = LoggerFactory.getLogger(FaxRES.class);

    @Autowired
    FaxService faxService;

    @RequestMapping(method = RequestMethod.POST)
    public String sendFax(@RequestBody FaxRequest faxRequest){
        LOG.info("Processing fax request: {}",faxRequest);
        return faxService.sendFax(faxRequest);
    }
}
