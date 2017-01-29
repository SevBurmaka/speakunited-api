package org.betsev.acp.business.forms.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by sevburmaka on 1/1/17.
 */
@RestController
@RequestMapping("/api/forms")
public class FormsRES {
    private static final Logger LOG = LoggerFactory.getLogger(FormsRES.class);

    //for now we are forwarding to the external api. once we can get a local instance up and running we will use that instead
    public static final String EXTERNAL_FORM_API = "https://congressforms.eff.org";
    public static final String RETRIEVE_FORM_ELEMENTS = "/retrieve-form-elements";
    public static final String FILL_OUT_FORM = "/fill-out-form";
    public static final String FILL_OUT_CAPTCHA = "/fill-out-captcha";


    @RequestMapping(value = RETRIEVE_FORM_ELEMENTS,method = RequestMethod.POST)
    public HashMap retrieveFormElements(@RequestBody HashMap data){
        LOG.info("Processing retrieve form elements request {}",data);
        return forwardRequest(data,EXTERNAL_FORM_API+RETRIEVE_FORM_ELEMENTS);
    }

    @RequestMapping(value = FILL_OUT_FORM,method = RequestMethod.POST)
    public HashMap fillOutForm(@RequestBody HashMap data){
        LOG.info("Processing fill out form request {}",data);
        return forwardRequest(data,EXTERNAL_FORM_API+FILL_OUT_FORM);
    }

    @RequestMapping(value = FILL_OUT_CAPTCHA,method = RequestMethod.POST)
    public HashMap fillOutCaptcha(@RequestBody HashMap data){
        LOG.info("Processing fill out captcha request {}",data);
        return forwardRequest(data,EXTERNAL_FORM_API+FILL_OUT_CAPTCHA);
    }

    private HashMap forwardRequest(HashMap data, String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url,data,HashMap.class);
    }


}
