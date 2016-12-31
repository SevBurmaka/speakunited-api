package org.betsev.acp;

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
public class ArmchairPoliticsApp  {
    public static final String GOOGLE_API_KEY ="AIzaSyBzrTWiTffGH9n6oIsDp_Lpd8mfTWDnbiI";
    private static final Logger LOG = LoggerFactory.getLogger(ArmchairPoliticsApp.class);

    @Autowired
    Environment env;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ArmchairPoliticsApp.class, args);

    }

}
