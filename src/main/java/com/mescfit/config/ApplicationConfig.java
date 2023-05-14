package com.mescfit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ComponentScan(basePackages = "com.mescfit")
public class ApplicationConfig {
}
