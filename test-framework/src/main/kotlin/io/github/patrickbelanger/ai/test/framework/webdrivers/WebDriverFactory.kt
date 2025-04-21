package io.github.patrickbelanger.ai.test.framework.webdrivers

import io.github.patrickbelanger.ai.test.framework.configurations.SeleniumConfiguration
import io.github.patrickbelanger.ai.test.framework.types.WebDrivers
import jakarta.annotation.PreDestroy
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class WebDriverFactory(private val seleniumConfiguration: SeleniumConfiguration) {

    val logger : Logger = LoggerFactory.getLogger(WebDriverFactory::class.java)

    private val targetWebDriver: ThreadLocal<WebDriver> = ThreadLocal()

    @Bean
    @Scope("prototype")
    fun getWebDriver(): WebDriver {
        logger.info("✨ Instantiate WebDriver: ${seleniumConfiguration.webDriver}")
        if (targetWebDriver.get() !== null) {
            return targetWebDriver.get()
        }

        targetWebDriver.set(
            when (seleniumConfiguration.webDriver) {
                WebDrivers.CHROME -> ChromeDriver()
                WebDrivers.EDGE -> EdgeDriver()
                WebDrivers.FIREFOX -> FirefoxDriver()
            }
        )
        return targetWebDriver.get()
    }

    fun quitWebDriver() {
        targetWebDriver.get()?.let {
            logger.info("🏁 Quitting WebDriver for thread: ${Thread.currentThread().name}")
            it.quit()
            targetWebDriver.remove()
        }
    }

    @PreDestroy
    fun shutdown() {
        logger.info("🚪 Application context is shutting down.")
        quitWebDriver()
    }
}