package com.bfchengnuo.uselibraries.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

/**
 * Druid 数据库连接池配置类
 *
 * @author Created by 冰封承諾Andy on 2019/9/18.
 */
@Configuration
public class DruidConfig {

    @Bean
    @Qualifier("druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pool")
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控
     * 配置一个管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = Maps.newHashMapWithExpectedSize(4);

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        // 默认就是允许所有访问
        initParams.put("allow","");
        initParams.put("deny","192.168.1.10");

        bean.setInitParameters(initParams);
        return bean;
    }


    /**
     * 配置一个web监控的 filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = Maps.newHashMapWithExpectedSize(1);
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Collections.singletonList("/*"));

        return  bean;
    }
}
