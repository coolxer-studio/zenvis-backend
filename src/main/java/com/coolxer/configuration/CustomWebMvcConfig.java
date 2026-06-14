package com.coolxer.configuration;

import com.coolxer.aop.AuthorityInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * web配置类
 *
 * @author hunter
 */
@Configuration
@Slf4j
public class CustomWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(authorityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/risk_index/**",
                        "/index.html",
                        "/static/**"
                );

    }

}
