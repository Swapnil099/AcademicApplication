package com.ubi.academicapplication.controller.config;

import com.ubi.academicapplication.util.AutogeneratePassword;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class CommonBeanConfig {

    @Bean
    ModelMapper createModelMapperBean(){
        return new ModelMapper();
    }

    @Bean
    SecureRandom createSecureRandomBean(){
        return new SecureRandom();
    }

    @Bean
    AutogeneratePassword createAutogeneratePassword(){
        return new AutogeneratePassword();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
