package com.springboot.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pictures/**")
                  .addResourceLocations("file:./resources/pictures/");
        
        registry.addResourceHandler("/avatars/**")
        .addResourceLocations("file:./resources/avatars/");
    }
}