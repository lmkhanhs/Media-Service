package com.lmkhanhs.media_service.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResouceConfig implements WebMvcConfigurer  {
    @Value("${media.image-dir}")
    private String imageDir;

    @Value("${media.video-dir}")
    private String videoDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + imageDir + "/");

        registry.addResourceHandler("/videos/**")
                .addResourceLocations("file:"+ videoDir+ "/");
    }
}
