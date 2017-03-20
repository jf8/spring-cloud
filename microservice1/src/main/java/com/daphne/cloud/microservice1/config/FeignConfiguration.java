package com.daphne.cloud.microservice1.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.daphne.cloud.microservice1")
public class FeignConfiguration {

}
