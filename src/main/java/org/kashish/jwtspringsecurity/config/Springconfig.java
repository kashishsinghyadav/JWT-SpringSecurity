package org.kashish.jwtspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Springconfig {


    @Bean
    // configuration of enabling suctomize filer chain
    //1.disable csrf token
    //2. make sure all request authticated
    //3. enable form login so the form is visible to acess page
    //4. enabling for post man or rest client
    //5. mark session as stateless
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> request.anyRequest().authenticated())
        .formLogin(Customizer.withDefaults())
        .httpBasic((Customizer.withDefaults())).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build(); // return the object of secuirty filer chain
    }

    @Bean
    // userdetail serivec is interface
    public UserDetailsService userDetailsService(){
        // userdetails also interface
        UserDetails user1 = User.withDefaultPasswordEncoder().username("kashish").password("<PASSWORD>").roles("USER").build();

        UserDetails user2 = User.withDefaultPasswordEncoder().username("admin").password("<PASSWORD>").roles("ADMIN").build();
        return  new InMemoryUserDetailsManager(user1,user2);
    }

}
