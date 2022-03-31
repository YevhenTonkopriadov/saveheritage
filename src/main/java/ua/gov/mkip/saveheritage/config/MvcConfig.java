package ua.gov.mkip.saveheritage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${fileresources.storange}")
    private String typeOfStorange;

    @Value("${fileresources.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (typeOfStorange.equals("fileSystem")) {
            registry.addResourceHandler("/img/**").
                    addResourceLocations("file://"+path+"/");
        }
    }
}
