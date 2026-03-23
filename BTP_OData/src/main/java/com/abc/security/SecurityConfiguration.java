package com.abc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    XsuaaServiceConfiguration xsuaaServiceConfiguration;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()   // ⭐ ADD THIS LINE
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/index.html").permitAll()
            .antMatchers("/controller/**").permitAll()
            .antMatchers("/view/**").permitAll()
            .antMatchers("/util/**").permitAll()
            .antMatchers("/**.js").permitAll()
            .antMatchers("/**.css").permitAll()
            .antMatchers("/vendor/**").permitAll()
            .antMatchers("/jatin.svc/**").hasAuthority("jatinDisplay")
            .antMatchers("/vendor").permitAll()
            .anyRequest().authenticated()
        .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(getJwtAuthoritiesConverter());
    }

    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthoritiesConverter() {
        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
        converter.setLocalScopeAsAuthorities(true); // not applicable in case of multiple xsuaa bindings!
        return converter;
    }

}