package com.learn.GeminiProject.projectConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurePlant {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
