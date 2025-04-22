package io.github.patrickbelanger.ai.test.framework.extensions

import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverFactory
import org.junit.jupiter.api.extension.*
import org.slf4j.LoggerFactory
import org.springframework.test.context.junit.jupiter.SpringExtension

class SeleniumJunitExtension : BeforeEachCallback, AfterEachCallback {

    private val logger = LoggerFactory.getLogger(SeleniumJunitExtension::class.java)

    override fun beforeEach(context: ExtensionContext) {
        logger.info("🚀 Prepare to launch test: ${context.displayName}")
        val applicationContext = SpringExtension.getApplicationContext(context)
        val webDriverFactory = applicationContext.getBean(WebDriverFactory::class.java)

        if (!WebDriverContext.isInitialized()) {
            val driver = webDriverFactory.getWebDriver()
            WebDriverContext.set(driver)
        }
    }

    override fun afterEach(context: ExtensionContext) {
        logger.info("🧹 Cleaning up test: ${context.displayName}")
        if (WebDriverContext.isInitialized()) {
            WebDriverContext.get().quit()
            WebDriverContext.remove()
        }
    }
}