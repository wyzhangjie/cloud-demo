package com.hyssop.framework.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author shuzongrui
 * @create 21/09/2018
 **/

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.hyssop.framework.repository", sqlSessionFactoryRef = "sqlSessionFactory")
public class SpringDataSourceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataSourceConfiguration.class);

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
//        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceTransactionManager annotationDrivenTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.hyssop.framework.entity");
        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:/repositories/**/*.xml"));
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("spring datasource init failed", e);
            throw new RuntimeException(e);
        }
    }

}
