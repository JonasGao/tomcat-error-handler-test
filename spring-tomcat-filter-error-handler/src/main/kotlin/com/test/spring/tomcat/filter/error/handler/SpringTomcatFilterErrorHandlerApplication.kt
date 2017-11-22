package com.test.spring.tomcat.filter.error.handler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.*

@SpringBootApplication
class SpringTomcatFilterErrorHandlerApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder =
            builder.sources(SpringTomcatFilterErrorHandlerApplication::class.java)
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringTomcatFilterErrorHandlerApplication::class.java, *args)
}

@Component
class MyFilter : Filter {
    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        throw IllegalStateException("This is a test exception")
    }

    override fun init(filterConfig: FilterConfig?) {
    }
}

@Controller class MyController {
    @GetMapping("/") fun index() = "index"
}