package com.qds.eurekaclientprovider.dicManage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author WangGuipin
 * @ClassName CorsConfig
 * @Description 跨域处理
 * @create 2019-03-20 11:41
 * @desc WangGuipin
 **/

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); //允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); //允许任何头
        corsConfiguration.addAllowedMethod("*"); //允许任何请求方法（post、get等）
        return  corsConfiguration;
    }

@Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource source = new  UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
