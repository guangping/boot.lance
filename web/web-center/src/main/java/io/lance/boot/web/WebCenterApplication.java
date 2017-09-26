package io.lance.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class WebCenterApplication {

    public static void main(String[] args) {
        System.out.println("注册中心 start ......");
        SpringApplication.run(WebCenterApplication.class, args);
    }
}
