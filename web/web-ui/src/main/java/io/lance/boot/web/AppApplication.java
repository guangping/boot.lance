package io.lance.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDiscoveryClient
@EnableAsync
@SpringBootApplication(scanBasePackages = {"io.lance.boot.common.*","io.lance.boot.web.*"})
public class AppApplication {

    public static void main(String[] args) {
        System.out.println("start ......");
        SpringApplication.run(AppApplication.class, args);
    }
}
