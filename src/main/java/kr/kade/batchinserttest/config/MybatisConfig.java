package kr.kade.batchinserttest.config;

import kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages = "kr.kade.batchinserttest.mapper")
public class MybatisConfig {

    private final DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public MapperFactoryBean<MybatisBatchInsertMapper> userMapper() throws Exception {
        MapperFactoryBean<MybatisBatchInsertMapper> factoryBean = new MapperFactoryBean<>(MybatisBatchInsertMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }
}
