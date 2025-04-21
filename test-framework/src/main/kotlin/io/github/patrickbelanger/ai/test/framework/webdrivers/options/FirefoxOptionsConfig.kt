package io.github.patrickbelanger.ai.test.framework.webdrivers.options

import io.github.patrickbelanger.ai.test.framework.configurations.SeleniumConfiguration
import org.openqa.selenium.firefox.FirefoxOptions
import org.springframework.stereotype.Component

@Component
class FirefoxOptionsConfig(config: SeleniumConfiguration)
    : BrowserOptionsBase(config) {

    fun create(): FirefoxOptions {
        val options = FirefoxOptions()
        configureAcceptInsecureCerts(options)
        return options
    }
}
