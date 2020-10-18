package com.just.oasystem.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * tag库数据连接
 *
 * @author luyu
 * @version v1.0
 * <p>
 * copyright 18994139782@163.com
 * @since 2020726
 */
@Configuration
@MapperScan(basePackages = OaDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oaSqlSessionFactory")
public class OaDataSourceConfig {

    static final String PACKAGE = "com.just.oasystem.*.dao";
    private static final String MAPPER_LOCATION = "classpath:mapper/*/*.xml";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver.class.name}")
    private String driverClass;

    @Bean(name = "oaDataSource")
    public DataSource oaDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(user);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "oaSqlSessionFactory")
    public SqlSessionFactory tagSqlSessionFactory(@Qualifier("oaDataSource") DataSource oaDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(oaDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OaDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
