package com.parking.lot.api.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@Getter
@Setter
@PropertySource("classpath:properties/openapi/open-api.properties")
@EnableConfigurationProperties(OpenAPIProperties.class)
@ConfigurationProperties(prefix = "open.api")
public class OpenAPIProperties {
    private Info info;
    private List<Server> servers;
}
