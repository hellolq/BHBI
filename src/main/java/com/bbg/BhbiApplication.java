package com.bbg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BhbiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BhbiApplication.class, args);
	}
}
