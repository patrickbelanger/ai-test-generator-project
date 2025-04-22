package io.github.patrickbelanger.ai.test.framework.webdrivers.options

import io.github.patrickbelanger.ai.test.framework.configurations.SeleniumConfiguration
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.stereotype.Component

@Component
class ChromeOptionsConfig(config: SeleniumConfiguration)
    : BrowserOptionsBase(config) {

    fun create(): ChromeOptions {
        val options = ChromeOptions()
        configureAcceptInsecureCerts(options)
        configureExperimentalPreferences(options)
        configureStartMaximized(options)
        return options
    }

    fun configureExperimentalPreferences(options: ChromeOptions) {
        val prefs = mapOf(
            "credentials_enable_service" to config.browserOptions.credentialsEnableService,
            "profile.password_manager_enabled" to config.browserOptions.passwordManagerLeakDetection
        )
        options.setExperimentalOption("prefs", prefs)
    }

    fun configureStartMaximized(options: ChromeOptions) {
        if (config.browserOptions.startMaximized) {
            options.addArguments("--start-maximized")
        }
    }
}