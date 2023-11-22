package com.example.movies.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// Configurando cors para conexão JS
// /** libera tudo
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    public void addCoreMappings(CorsRegistry registry){
        registry.addMapping("/**");
    }
}
