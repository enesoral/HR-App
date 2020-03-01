package com.enesoral.simplehr.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] publicLinks = {"/login", "/", "/index", "/images/**", "/css/**", "/webjars/**"};
        String[] adminLinks = {"/h2-console/**", "/**/delete/", "/**/addform/", "/**/addjob/", "/applications/index", "**/showresume"};
        String[] userLinks = {"/**/resumeform/", "/**/applyform/"};

        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(publicLinks).permitAll()
                .antMatchers(adminLinks).access("hasAuthority('ADMIN')")
                .antMatchers(userLinks).access("hasAuthority('USER')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/jobs/")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/index?logout").permitAll();
    }
}