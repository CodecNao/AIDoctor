package com.AIDoc.AIDoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.AIDoc.AIDoc")
public class AiDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiDocApplication.class, args);
	}

}

