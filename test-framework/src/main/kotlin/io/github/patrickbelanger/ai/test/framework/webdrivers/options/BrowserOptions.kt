package io.github.patrickbelanger.ai.test.framework.webdrivers.options

import io.github.patrickbelanger.ai.test.framework.configurations.SeleniumConfiguration
import org.openqa.selenium.remote.AbstractDriverOptions

abstract class BrowserOptionsBase(
    protected val config: SeleniumConfiguration
) {
    protected fun configureAcceptInsecureCerts(options: AbstractDriverOptions<*>) {
        options.setAcceptInsecureCerts(config.browserOptions.acceptInsecureCerts)
    }
}