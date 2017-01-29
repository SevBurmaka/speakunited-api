package org.betsev.acp.business.fax.entity;

import com.phaxio.Fax;
import com.phaxio.Phaxio;
import com.phaxio.exception.PhaxioException;
import org.betsev.acp.business.contact.boundary.ContactService;
import org.betsev.acp.business.contact.entity.Contact;
import org.betsev.acp.business.fax.boundary.FaxService;
import org.betsev.acp.config.ApiKeyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by sevburmaka on 1/14/17.
 */
@Service
public class PhaxioFaxService  implements FaxService{
    private static final Logger LOG = LoggerFactory.getLogger(PhaxioFaxService.class);

    @Autowired
    ApiKeyConfig apiKeyConfig;

    @Autowired
    @Qualifier("unified")
    ContactService contactService;

    @Override
    public String sendFax(FaxRequest request) {
        Phaxio.apiKey = apiKeyConfig.getPhaxioApiKey();
        Phaxio.apiSecret = apiKeyConfig.getPhaxioApiSecret();

        if (request.getHeader().length() > 300 || request.getBody().length() > 3000)
            return "Let's not get too crazy with the fax size here ;)";

        Map<String,Object> options = new HashMap();

        List<String> phoneNumbers = getPhones(request.getBioguideId());
        if (CollectionUtils.isEmpty(phoneNumbers))
            return "Could not look up fax number for bioguideId: "+request.getBioguideId(); //@todo give a more detailed response if sending faxes fails for any reason

        List<File> files = new ArrayList();
        final File tmpFile = createHtmlFile(request);
        files.add(tmpFile);
        RetryTemplate template = new RetryTemplate();

        //@todo this should be refactored into some single threaded consumer service
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(4);
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000L);
        backOffPolicy.setMultiplier(2);
        backOffPolicy.setMaxInterval(4000L);

        template.setRetryPolicy(policy);
        template.setBackOffPolicy(backOffPolicy);
        String responseString;
        try {
            Long result = template.execute(new RetryCallback<Long,PhaxioException>() {
                public Long doWithRetry(RetryContext context) throws PhaxioException{
                    // Do stuff that might fail, e.g. webservice operation
                    Long id = doSendFax(phoneNumbers,files,options);
                    LOG.info("Created fax with faxId {} from request {}",id,request);
                    return id;
                }
            });
            responseString = "Fax success!";
        }catch (PhaxioException e){
            LOG.error("Error creating fax from request {}: phoneNumbers: {} : ",request,phoneNumbers,e);
            responseString = "Fax failed :( : "+e.getMessage();
        }

        //delete the file after a few seconds
        deleteOnAfterDelay(tmpFile);

        return responseString;
    }


    List<String> getPhones(String bioguideId){
        ArrayList<String> phones = new ArrayList<>();
        Contact contact = contactService.getByBioguideId(bioguideId);

        if (!StringUtils.isEmpty(contact.getDcFax()))
            phones.add(contact.getDcFax());
        if (!StringUtils.isEmpty(contact.getDistrictFax()))
            phones.add(contact.getDistrictFax());

        return phones;
    }

    private void deleteOnAfterDelay(File f){
        Runnable task2 = () -> {
            try {
                Thread.sleep(10000);
            }catch(Exception e){}
            f.delete();
        };
        new Thread(task2).start();
    }

    private File createHtmlFile(FaxRequest request){
        UUID nameId = UUID.randomUUID();
        File tmp = new File(nameId+".html");
        try {
            FileWriter writer = new FileWriter(tmp);
            writer.write("<P align=right >"+request.getName()+"</br>");
            writer.write(request.getAddress()+"</br>");
            writer.write("</P>");
            writer.write("<h2>"+request.getHeader()+"</h2>");
            writer.write("<body>"+request.getBody() +"</body>");
            writer.write("<P align=left > Sincerely, </br>"+request.getName()+"</br>");
            writer.flush();
            writer.close();
        }catch (Exception e){
            LOG.error("Error while attempting to create file for {}",request);
        }
        return tmp;
    }

    private Long doSendFax(List<String> phoneNumbers, List<File> files, Map<String,Object> options) throws PhaxioException{
         return Fax.send(phoneNumbers, files, options);
    }
}
