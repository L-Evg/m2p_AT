package com.m2p.at.ftbtests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
/*@EntityScan("com.m2p.at.ftbtests.data.model")
@EnableJpaRepositories*/
public class FtbTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtbTestsApplication.class, args);
	}

}
