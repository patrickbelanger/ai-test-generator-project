package io.github.patrickbelanger.ai.test.framework.configurations

import io.github.patrickbelanger.ai.test.framework.types.SupportedBrowser
import org.openqa.selenium.PageLoadStrategy
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "selenium")
class SeleniumConfiguration {
    lateinit var grid: SeleniumGridConfiguration
    lateinit var browser: SupportedBrowser
    lateinit var browserOptions: SeleniumBrowserOptions
}

class SeleniumGridConfiguration {
    var enabled: Boolean = false
    var host: String = "http://localhost:4441"
}

class SeleniumBrowserOptions {
    var acceptInsecureCerts: Boolean = false
    /* Chrome Browser */
    var credentialsEnableService: Boolean = false
    var pageLoadStrategy: PageLoadStrategy = PageLoadStrategy.NORMAL
    /* Chrome Browser */
    var passwordManagerLeakDetection: Boolean = false
    var startMaximized: Boolean = true
}