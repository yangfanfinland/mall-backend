package com.sailtheocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// Scan mybatis general mapper packages
@MapperScan(basePackages = "com.sailtheocean.mapper")
// Scan all packages and related component packages
@ComponentScan(basePackages = {"com.sailtheocean", "org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
