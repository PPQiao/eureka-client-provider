package com.qds.eurekaclientprovider.TestServiceImpl;

import com.qds.eurekaclientprovider.testService.EurekaServiceDemo;
import org.springframework.stereotype.Service;

@Service
public class EurekaServiceDemoImpl implements EurekaServiceDemo {

    @Override
    public String RemoteHello() {
        return "Hello! --from 127.0.0.1:7002/eureka-client-provider";
    }
}
