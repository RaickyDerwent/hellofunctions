package com.example.hellofunctions;

import com.example.avrocdrgen.types.CDRFile;
import com.example.avrocdrgen.types.CDRFileGeneratedEvent;
import com.example.avrocdrgen.types.FTPInfo;
import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class HellofunctionsApplication {

	public static void main (String[] args) {
		SpringApplication.run(HellofunctionsApplication.class, args);
	}

	@Bean
	Supplier<CDRFileGeneratedEvent> getProducts () {
		return () -> {
			final Faker instance = Faker.instance();

			return CDRFileGeneratedEvent.newBuilder()
					.setResellerId(instance.name().firstName())
					.setCustomerId(instance.name().lastName())
					.setSourceFTP(FTPInfo.newBuilder()
							              .setHost(instance.address().city())
							              .setPath(instance.address().streetName())
							              .build())
					.setCdrFiles(Collections.singletonList(CDRFile.newBuilder()
							                                       .setFileName(instance.file().fileName())
							                                       .build()))
					.build();
		};
	}

}
