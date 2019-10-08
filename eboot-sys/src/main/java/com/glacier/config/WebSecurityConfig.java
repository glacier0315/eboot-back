package com.glacier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * @author hebin
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/favicon.ico");
        web.ignoring().antMatchers("/error");
        super.configure(web);
    }

    /**
     * 置user-detail服务
     * <p>
     * 方法描述
     * accountExpired(boolean)                定义账号是否已经过期
     * accountLocked(boolean)                 定义账号是否已经锁定
     * and()                                  用来连接配置
     * authorities(GrantedAuthority...)       授予某个用户一项或多项权限
     * authorities(List)                      授予某个用户一项或多项权限
     * authorities(String...)                 授予某个用户一项或多项权限
     * disabled(boolean)                      定义账号是否已被禁用
     * withUser(String)                       定义用户的用户名
     * password(String)                       定义用户的密码
     * roles(String...)                       授予某个用户一项或多项角色
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        // 配置指定用户权限信息  通常生产环境都是从数据库中读取用户权限信息而不是在这里配置
        //auth.inMemoryAuthentication().withUser("username1").password("123456").roles("USER").and().withUser("username2").password("123456").roles("USER","AMDIN");

        // ****************   基于数据库中的用户权限信息 进行认证
        //指定密码加密所使用的加密器为 bCryptPasswordEncoder()
        //需要将密码加密后写入数据库
        // myUserDetailService 类中获取了用户的用户名、密码以及是否启用的信息，查询用户所授予的权限，用来进行鉴权，查询用户作为群组成员所授予的权限
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);

    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨站请求防护
        http.cors().and().csrf().disable()
                // 基于token，所以不需要session  如果基于session 则表使用这段代码
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //对请求进行认证
                //url认证配置顺序为：1.先配置放行不需要认证的 permitAll() 2.然后配置 需要特定权限的 hasRole() 3.最后配置 anyRequest().authenticated()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("oauth/**").permitAll()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //
                .antMatchers("/webjars/**").permitAll()
                // 查看sql监控 druid
                .antMatchers("/druid/**").permitAll()
                // 首页
                .antMatchers("/").permitAll()
                // 登录页面
                .antMatchers("/login").permitAll()
                // swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                // 验证码
                .antMatchers("/kaptcha/**").permitAll()
                // 服务监控
                .antMatchers("/actuator/**").permitAll()
                //其他请求都需要进行认证,认证通过够才能访问
                // 待考证：如果使用重定向 httpServletRequest.getRequestDispatcher(url).forward(httpServletRequest,httpServletResponse); 重定向跳转的url不会被拦截（即在这里配置了重定向的url需要特定权限认证不起效），但是如果在Controller 方法上配置了方法级的权限则会进行拦截
                .anyRequest().authenticated();
        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

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
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
