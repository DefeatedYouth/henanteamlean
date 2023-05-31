package com.team.app.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigureAdapter implements WebMvcConfigurer {



    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
        configurer.favorPathExtension(false);
    }




    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //ip限制
    }

    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        // fixme 测试图片
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("classpath:/pic/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 不需要拦截的地址
     * @return
     */
    private List<String> getNoInterceptorUrlsForLogin() {
        List<String> urls = new ArrayList<>();
        //swagger
        urls.add("/favicon.ico");
        urls.add("/error");
        urls.add("/swagger-resources/**");
        urls.add("/webjars/**");
        urls.add("/v2/**");
        urls.add("/doc.html");
        urls.add("**/swagger-ui.html");
        urls.add("/swagger-ui.html/**");
        //controller
        urls.add("/login/*");
        // fixme 测试图片
        urls.add("/pic/**");
        //测试图片
        urls.add("/getUrl");
        return urls;
    }
}
