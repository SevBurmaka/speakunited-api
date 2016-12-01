package org.betsev.acp;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Created by sevburmaka on 11/30/16.
 */
@SpringBootApplication
@ComponentScan
public class ArmchairPoliticsApp  {

    private static final Logger LOG = LoggerFactory.getLogger(ArmchairPoliticsApp.class);

    @Autowired
    Environment env;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ArmchairPoliticsApp.class, args);

    }

}
