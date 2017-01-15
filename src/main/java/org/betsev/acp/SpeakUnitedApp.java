package org.betsev.acp;

import org.betsev.acp.business.contact.boundary.USCLContactService;
import org.betsev.acp.business.contact.control.UnifiedContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * Created by sevburmaka on 11/30/16.
 */
@SpringBootApplication
@ComponentScan
public class SpeakUnitedApp {
    private static final Logger LOG = LoggerFactory.getLogger(SpeakUnitedApp.class);

    @Autowired
    Environment env;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpeakUnitedApp.class, args);
        ctx.getBean(UnifiedContactRepository.class).unifyContacts(ctx.getBean(USCLContactService.class).getAllContacts());
        
    }


}
