package com.github.bhrother.oauth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan

@EnableConfigurationProperties
@SpringBootApplication
@EnableAutoConfiguration(exclude = [EmbeddedMongoAutoConfiguration.class])
@ComponentScan(['com.github.bhrother.oauth'])
class Application {

	static void main(String[] args) {
		SpringApplication.run Application, args
	}
}
