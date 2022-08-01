package com.works.customerrestapibasicsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CustomerRestApiBasicSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerRestApiBasicSecurityApplication.class, args);
    }

}
