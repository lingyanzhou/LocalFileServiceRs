package com.westlakefinancial.technology.LocalFileServiceRs

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication(exclude= arrayOf(DataSourceAutoConfiguration::class))
class LocalFileServiceRsApplication

fun main(args: Array<String>) {
    SpringApplication.run(LocalFileServiceRsApplication::class.java, *args)
}
