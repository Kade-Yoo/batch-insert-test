package kr.kade.batchinserttest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    @Autowired
    public JdbcTemplateConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(dataSource);
    }
}
