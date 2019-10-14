package com.glacier.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-08-04 10:03
 */
@Configuration
public class DruidConfiguration {
    private static final String DB_PREFIX = "spring.datasource";

    /**
     * 配置数据源
     * ConfigurationProperties(prefix = DB_PREFIX)  读取相关的属性配置
     * @return
     */
    @ConfigurationProperties(prefix = DB_PREFIX)
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置一个管理后台的Servlet,配置Druid的监控
     * @return
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>(5);
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置一个web监控的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>(1);
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
