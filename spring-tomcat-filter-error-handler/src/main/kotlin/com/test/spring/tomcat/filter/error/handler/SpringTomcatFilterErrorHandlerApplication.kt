package com.test.spring.tomcat.filter.error.handler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringTomcatFilterErrorHandlerApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringTomcatFilterErrorHandlerApplication::class.java, *args)
}
