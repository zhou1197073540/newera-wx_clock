package com.myclock.newera;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.myclock.newera.mapper")
@EnableScheduling
@SpringBootApplication
public class MyclockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyclockApplication.class, args);
	}
}
