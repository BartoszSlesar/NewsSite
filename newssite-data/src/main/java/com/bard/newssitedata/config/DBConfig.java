package com.bard.newssitedata.config;



import com.bard.newssitedata.model.ArticleRowMapper;
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
    public ArticleRowMapper carRowMapper(){
        return new ArticleRowMapper();
    }
}
