package com.ufes.inf.dwws.umdb.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //TODO: bloqueio de rotas abertas e fechadas
//    //TODO: autenticação de rotas fechadas por role de user
//    @Override
//    protected  void configure(HttpSecurity http) throws  Exception{
//        http.authorizeRequests()
//                .antMatchers("/api/**").permitAll();
//    }
//}

