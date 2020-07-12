package com.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Lee
 * @date 2020/1/14 23:27
 * @description Swagger的配置类
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * 设置是否显示接口文档
     */
    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi(Environment environment) {

        /**
         * 设置要显示文档的环境（或者采用上面的属性注入的方式）
         */
//        Profiles profiles = Profiles.of("dev", "test");
//        boolean enable = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable).groupName("xx")
                .select()
                //Controller所在包(必须新建包)
                .apis(RequestHandlerSelectors.basePackage("com.cloud.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //接口文档的名字
                .title("xuecloud api")
                //接口文档的描述
                .description("xuecloud api接口文档")
                //服务条款网址
                .termsOfServiceUrl("http://localhost/")
                //接口文档的版本
                .version("1.0.0")
                // 接口文档维护联系信息
                .contact(new Contact("lee", "", "lee@163.com"))
                .build();
    }
}
