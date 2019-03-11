package com.example.demo.ctx;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
               // .mvcMatchers("/","/index","/public","/public/index")
                .antMatchers("/*","/public/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                //.httpBasic()
                .formLogin()
                .successForwardUrl("/admin")
                .and()
                .logout().logoutSuccessUrl("/public")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder(10, new SecureRandom()))
                .withUser("admin")
                .password("$2a$10$vzZzEX6HXyphUZEXoADVxeWIROWBA8aevSwelG2HyRLzO4N6gEN8S")
                .roles("ADMIN")
                .and()
                .withUser("manager")
                .password("$2a$10$vzZzEX6HXyphUZEXoADVxeWIROWBA8aevSwelG2HyRLzO4N6gEN8S")
                .credentialsExpired(true)
                .accountExpired(true)
                .accountLocked(true)
                .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES")
                .roles("MANAGER");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String hello = new BCryptPasswordEncoder(10, new SecureRandom()).encode("zte@123456");
            System.out.println(hello);
        }
    }
}
