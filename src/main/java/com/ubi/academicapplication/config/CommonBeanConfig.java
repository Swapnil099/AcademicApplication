package com.ubi.academicapplication.config;

import com.ubi.academicapplication.util.AutogeneratePassword;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
