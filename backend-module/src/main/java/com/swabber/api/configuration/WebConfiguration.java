package com.swabber.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// todo rename propertie file
@Configuration
@PropertySource("classpath:properties/application-${spring.profiles.active}.properties")
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${parking.lot.frontend.host}")
    private String frontHost;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontHost);
    }
}
