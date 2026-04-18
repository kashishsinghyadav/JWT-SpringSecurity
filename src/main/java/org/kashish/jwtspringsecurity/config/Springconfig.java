package org.kashish.jwtspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Springconfig {


    @Bean
    // configuration of enabling suctomize filer chain
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.build(); // return the object of secuirty filer chain
    }
}
