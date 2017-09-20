package io.lance.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableAsync
@SpringBootApplication(scanBasePackages = {"io.lance.boot.common.*","io.lance.boot.web.*"})
public class AppApplication {


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        System.out.println("service consumer start ...");
        SpringApplication.run(AppApplication.class, args);
    }
}
