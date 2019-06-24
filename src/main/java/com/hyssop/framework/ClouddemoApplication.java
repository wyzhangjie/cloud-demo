package com.hyssop.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhjie.zhang
 */
@SpringBootApplication
@MapperScan("com.hyssop.framework.mapper")
public class ClouddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClouddemoApplication.class, args);
	}

}

