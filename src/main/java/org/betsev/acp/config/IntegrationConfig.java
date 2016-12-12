package org.betsev.acp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

/**
 * Created by sevburmaka on 12/11/16.
 */
@Configuration
@EnableIntegration
@ImportResource("config/integration/integration-context.xml")
public class IntegrationConfig {
}
