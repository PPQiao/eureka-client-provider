package com.qds.eurekaclientprovider.testController;

import com.qds.eurekaclientprovider.testService.EurekaServiceDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class EurekaControllerDemo {

    @Autowired
    EurekaServiceDemo eurekaServiceDemo;

    @RequestMapping("/sayHello")
    @ResponseBody
    public String RemoteHello(){
        System.out.println(eurekaServiceDemo.RemoteHello());
        return eurekaServiceDemo.RemoteHello();
    }
}
