package io.lance.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerApplication {

	public static void main(String[] args) {
		System.out.println("Zipkin Server start ....");
		SpringApplication.run(ZipkinServerApplication.class, args);
	}
}
