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
    public Docket createRestApi1(Environment environment) {

        /**
         * 设置要显示文档的环境（或者采用上面的属性注入的方式）
         */
        Profiles profiles = Profiles.of("dev", "test");
        boolean isActive = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .groupName("lee")
                .select()
                //Controller所在包(必须新建包)
                .apis(RequestHandlerSelectors.basePackage("com.cloud.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket createRestApi2(Environment environment) {

        /**
         * 设置要显示文档的环境（或者采用上面的属性注入的方式）
         */
        Profiles profiles = Profiles.of("dev", "test");
        boolean isActive = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .groupName("Lee2")
                .select()
                //Controller所在包(必须新建包)
                .apis(RequestHandlerSelectors.basePackage("com.cloud.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("xuecloud项目接口文档")
                // 描述
                .description("xuecloud项目")
                // 联系
                .contact(new Contact("Lee", "http://www.baidu.com","lee@qq.com"))
                .version("1.1.0")// 版本
                .build();
    }
}
