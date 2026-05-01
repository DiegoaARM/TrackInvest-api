package com.trackinvest.account.common.infrastructure.config;

import com.trackinvest.account.common.infrastructure.handler.UserContextInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserContextInterceptor userContextInterceptor;

    public WebConfig(UserContextInterceptor userContextInterceptor) {
        this.userContextInterceptor = userContextInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Asegúrate de que el path coincida con tus controladores
        // Si tus endpoints empiezan con /wallets, usa "/wallets/**" o "/api/**"
        registry.addInterceptor(userContextInterceptor)
                .addPathPatterns("/**");
    }
}
