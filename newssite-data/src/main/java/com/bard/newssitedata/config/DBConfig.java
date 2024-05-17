package com.bard.newssitedata.config;


import com.bard.newssitedata.model.NewsRowMapper;
import com.bard.newssitedata.utils.ArticleNewsConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DBConfig {

    private final DataSource dataSource;


    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(this.dataSource);
    }

    @Bean
    public NewsRowMapper newsRowMapper() {
        return new NewsRowMapper();
    }


    @Bean
    public ArticleNewsConverter articleNewsConverter() {
        return new ArticleNewsConverter();
    }
}
