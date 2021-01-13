//package com.gjsyoung.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMessage;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;
//
//import javax.sql.DataSource;
//
///**
// * @author cairuojin
// * @create 2019-04-20 22:35
// */
//@Configuration
//@EnableWebMvcSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//
//    //使用内存维护用户存储
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER","ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/admin/*")
//                    .authenticated()
//                .anyRequest()
//                    .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .logout()
//                    .logoutSuccessUrl("/index")
//                .and()
//                .rememberMe()
//                    .tokenValiditySeconds(2419200)
//                    .key("mySecurityKey");
//    }
//
//    //    //使用数据库维护用户存储
////    @Autowired
////    DataSource dataSource;
////
////    private static final String USER_USERNAME_QUERY = "select username, password, enabled from myuser where username = ?";
////    private static final String ANTHORITIES_USERNAME_QUERY = "select username, anthority from myuser where username = ?";
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery(USER_USERNAME_QUERY)
////                .authoritiesByUsernameQuery(ANTHORITIES_USERNAME_QUERY)
////                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
////    }
//
//
////    //使用LDAP维护用户存储
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .ldapAuthentication()
////                .userSearchBase("ou = people")
////                .userSearchFilter("(uid = {0})")
////                .groupSearchBase("ou = groups")
////                .groupSearchFilter("member = {0}")
////                .contextSource()
////                    .root("dc=habuma,dc=com")
////                    .ldif("classpath:users.ldif");
////    }
//
////    //使用自定义配置维护用户存储
////    @Autowired
////    UserDetailsService userDetailService;
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .userDetailsService(userDetailService);
////    }
//}