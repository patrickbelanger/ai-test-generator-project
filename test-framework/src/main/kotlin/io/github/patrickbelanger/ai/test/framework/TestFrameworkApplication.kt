package io.github.patrickbelanger.ai.test.framework

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties

@ConfigurationPropertiesScan
@EnableConfigurationProperties
@SpringBootApplication
class TestFrameworkApplication {
    fun main(args: Array<String>) {
        SpringApplicationBuilder(TestFrameworkApplication::class.java)
            .web(WebApplicationType.NONE)
            .run(*args)
    }
}