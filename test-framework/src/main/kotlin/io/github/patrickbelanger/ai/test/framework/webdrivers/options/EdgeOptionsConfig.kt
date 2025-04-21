package io.github.patrickbelanger.ai.test.framework.webdrivers.options

import io.github.patrickbelanger.ai.test.framework.configurations.SeleniumConfiguration
import org.openqa.selenium.edge.EdgeOptions
import org.springframework.stereotype.Component

@Component
class EdgeOptionsConfig(config: SeleniumConfiguration)
    : BrowserOptionsBase(config) {

    fun create(): EdgeOptions {
        val options = EdgeOptions()
        configureAcceptInsecureCerts(options)
        configureStartMaximized(options)
        return options
    }

    fun configureStartMaximized(options: EdgeOptions) {
        if (config.browserOptions.startMaximized) {
            options.addArguments("--start-maximized")
        }
    }
}