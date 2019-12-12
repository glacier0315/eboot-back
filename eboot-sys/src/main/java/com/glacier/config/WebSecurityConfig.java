package com.glacier.config;

import com.glacier.security.filter.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @description 权限认证配置
 * @date 2019-09-30 15:51
 */
@Configuration
@EnableWebSecurity // 开启security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenFilter jwtTokenFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/favicon.ico", "/error", "/static/**", "/webjars/**");
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/oauth/**").permitAll()
                // 查看sql监控 druid
                .antMatchers("/druid/**").permitAll()
                // 登录
                .antMatchers("/login", "/logout").permitAll()
                // swagger
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs",
                        "/webjars/springfox-swagger-ui/**").permitAll()
                // 验证码
                .antMatchers("/kaptcha/**").permitAll()
                // 服务监控
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated();
        http.cors().and().csrf().disable(); // 禁用自带的跨域管理

        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        // tolen 验证过滤器
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
