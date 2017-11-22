package com.test.spring.tomcat.filter.error.handler

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
class SpringTomcatFilterErrorHandlerApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder =
            builder.sources(SpringTomcatFilterErrorHandlerApplication::class.java)
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringTomcatFilterErrorHandlerApplication::class.java, *args)
}

@Component
@Order(0)
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

@Component
@ControllerAdvice
class ExceptionHandler : AbstractHandlerExceptionResolver() {

    override fun doResolveException(request: HttpServletRequest?, response: HttpServletResponse, handler: Any?, ex: java.lang.Exception?): ModelAndView? {
        if (!response.isCommitted) {
            response.reset()
        }

        response.writer.use {
            println("Hello, Exception!")
        }

        return null
    }

    @ExceptionHandler(Exception::class) fun handler(response: HttpServletResponse) {
        if (!response.isCommitted) {
            response.reset()
        }

        response.writer.use {
            println("Hello, Exception!")
        }
    }
}