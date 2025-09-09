package com.project.feedback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityDemo
{
    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    //disable csrf
http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/getuser").permitAll() 
        .anyRequest().authenticated()
    )
    .httpBasic(Customizer.withDefaults());
    return http.build();
    
    }

    //verify user and admin
    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user=User.builder().username("jana").password(passwordEncoder().encode("Janajd_14")).roles("FACULTY").build();
         UserDetails admin=User.builder().username("janaadmin").password(passwordEncoder().encode("Jadmin")).roles("ADMIN").build();
         return new InMemoryUserDetailsManager(user,admin);

    }
 }