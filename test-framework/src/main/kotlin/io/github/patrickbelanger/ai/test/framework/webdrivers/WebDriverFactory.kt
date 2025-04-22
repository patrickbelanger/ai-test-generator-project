package io.github.patrickbelanger.ai.test.framework.webdrivers

import io.github.patrickbelanger.ai.test.framework.configurations.SeleniumConfiguration
import io.github.patrickbelanger.ai.test.framework.types.SupportedBrowser
import io.github.patrickbelanger.ai.test.framework.webdrivers.options.ChromeOptionsConfig
import io.github.patrickbelanger.ai.test.framework.webdrivers.options.EdgeOptionsConfig
import io.github.patrickbelanger.ai.test.framework.webdrivers.options.FirefoxOptionsConfig
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class WebDriverFactory(
    private val seleniumConfiguration: SeleniumConfiguration,
    private val chromeOptions: ChromeOptionsConfig,
    private val edgeOptions: EdgeOptionsConfig,
    private val firefoxOptions: FirefoxOptionsConfig
) {

    val logger : Logger = LoggerFactory.getLogger(WebDriverFactory::class.java)

    @Bean
    @Scope("prototype")
    fun getWebDriver(): WebDriver {
        logger.info("✨ Instantiate WebDriver: ${seleniumConfiguration.browser}")

        return if (seleniumConfiguration.grid.enabled) {
            val options = when (seleniumConfiguration.browser) {
                SupportedBrowser.CHROME -> chromeOptions.create()
                SupportedBrowser.FIREFOX -> firefoxOptions.create()
                SupportedBrowser.EDGE -> edgeOptions.create()
            }
            logger.info("ℹ️ Using remote WebDriver at ${seleniumConfiguration.grid.host}")
            val webdriver = RemoteWebDriver.builder()
                .addAlternative(options)
                .address(seleniumConfiguration.grid.host)
                .build()
            if (webdriver is FirefoxDriver) { // TODO: This logic is duplicated and can be simplified
                webdriver.manage().window().maximize()
            }
            webdriver
        } else {
            logger.info("ℹ️ Using local WebDriver at ${seleniumConfiguration.browser}")
            when (seleniumConfiguration.browser) {
                SupportedBrowser.CHROME -> ChromeDriver(chromeOptions.create())
                SupportedBrowser.EDGE -> EdgeDriver(edgeOptions.create())
                SupportedBrowser.FIREFOX -> {
                    val webdriver = FirefoxDriver(firefoxOptions.create())
                    if (seleniumConfiguration.browserOptions.startMaximized) {
                        webdriver.manage().window().maximize()
                    }
                    webdriver
                }
            }
        }
    }
}