package kr.kade.batchinserttest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "kr.kade.batchinserttest.mapper")
public class AppConfig {
}
