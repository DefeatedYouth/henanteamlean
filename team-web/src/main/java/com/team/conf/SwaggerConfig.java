package com.team.conf;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * description: SwaggerConfig
 * date: 2020/4/14 16:05
 * version: 1.0
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        Predicate<RequestHandler> requestHandlerPredicate = basePackage(
                "com.team.controller"
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select()
                .apis(requestHandlerPredicate)
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("河南班组精益化管理 API")
                        .description("河南班组精益化管理 API 文档详细信息")
                        .version("1.0")
                        .contact(new Contact("NJZM", "", ""))
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build())
                        .globalOperationParameters(getParameterList());

    }

    private List<Parameter> getParameterList() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        parameterBuilder.name("_at").description("令牌").modelRef(new ModelRef("String"))
                .parameterType("header").required(false).build();//header 非必填
        pars.add(parameterBuilder.build());
        return pars;
    }

    public static Predicate<RequestHandler> basePackage(final String... basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String... basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
