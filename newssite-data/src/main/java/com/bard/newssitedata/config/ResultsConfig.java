package com.bard.newssitedata.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "results")
@Getter
@Setter
public class ResultsConfig {

    private int limit;
}
