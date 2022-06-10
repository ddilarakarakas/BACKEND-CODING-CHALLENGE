package com.weatherproject.security;

import com.weatherproject.filter.CustomAuthenticationFilter;
import com.weatherproject.filter.CustomAuthorizationFilter;
import com.weatherproject.service.ApiUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final ApiUserService apiUserService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), apiUserService);
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/**").hasAuthority("USER_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("USER_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority("USER_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("USER_ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.POST, "/weather/**").hasAuthority("USER_ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/weather/**").hasAnyAuthority("USER_ADMIN", "USER_END");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/weather/delete").hasAuthority("USER_ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
