package kr.kade.batchinserttest.config;

import kr.kade.batchinserttest.mapper.MybatisBatchInsertMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages = "kr.kade.batchinserttest.mapper")
public class MybatisConfig {

    private final DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setMapperLocations(resolveMapperLocations());
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public MapperFactoryBean<MybatisBatchInsertMapper> userMapper() throws Exception {
        MapperFactoryBean<MybatisBatchInsertMapper> factoryBean = new MapperFactoryBean<>(MybatisBatchInsertMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }

    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath:mapper/*.xml");
        List<Resource> resources = new ArrayList<>();
        for (String mapperLocation : mapperLocations) {
            try {
                Resource[] mappers = resourceResolver.getResources(mapperLocation);
                resources.addAll(Arrays.asList(mappers));
            } catch (IOException e) {
//                    log.error("Mybatis resources Get Exception Occur", e);
            }
        }
        return resources.toArray(new Resource[0]);
    }
}
