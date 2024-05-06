package com.bard.newssitedata.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@ConfigurationProperties(prefix = "news")
@Getter
@Setter
public class NewsApiConfig {


    private String apiKey;


}
