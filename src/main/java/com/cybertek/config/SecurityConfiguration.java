package com.cybertek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // authorization
                .antMatchers("/admin/**").hasAuthority("Admin")
                .antMatchers("/manager/**").hasAnyAuthority("Admin", "Manager")
                .antMatchers("/employee/**").hasAnyAuthority("Admin", "Employee")
                .antMatchers(
                        "/",
                        "/login",
                        "/welcome",
                        "/fragments/**",
                        "/assets/**",
                        "/images/**"
                ).permitAll()
                .and()
                // login
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error=true")
                .permitAll()
                // logout
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout=true")
                .permitAll();


    }

}
