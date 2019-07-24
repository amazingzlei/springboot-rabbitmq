package com.fh;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 开启RabbitMq模式
@EnableRabbit
@SpringBootApplication
public class Springboot07RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot07RabbitmqApplication.class, args);
	}

}
