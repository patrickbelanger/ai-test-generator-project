package io.github.patrickbelanger.ai.test.framework.configurations

import io.github.patrickbelanger.ai.test.framework.types.WebDrivers
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "selenium")
class SeleniumConfiguration {
    lateinit var webDriver: WebDrivers
}