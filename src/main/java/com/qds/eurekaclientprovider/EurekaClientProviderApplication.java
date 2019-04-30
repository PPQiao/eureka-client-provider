package com.qds.eurekaclientprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient//作为微服务eureka客户端
@EnableTransactionManagement//开启注解扫描
@EnableSwagger2//开启swagger
public class EurekaClientProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProviderApplication.class, args);
    }

}
