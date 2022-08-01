package com.works.customerrestapibasicsecurity.config;

import com.works.customerrestapibasicsecurity.services.RepairService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final RepairService rService;
    public SecurityConfig(RepairService rService) {
        this.rService = rService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(rService).passwordEncoder(rService.encoder());
    }

    // role ->
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/repair/**").hasRole("repairman")
                .antMatchers("/customer/**").hasRole("customer")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}