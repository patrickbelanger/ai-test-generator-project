package io.github.patrickbelanger.ai.test.framework.extensions

import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.junit.jupiter.api.extension.*
import org.slf4j.LoggerFactory

class SeleniumJunitExtension : BeforeEachCallback, AfterEachCallback {

    private val logger = LoggerFactory.getLogger(SeleniumJunitExtension::class.java)

    override fun beforeEach(context: ExtensionContext) {
        logger.info("🚀 Prepare to launch test: ${context.displayName}")
        WebDriverContext.get()
    }

    override fun afterEach(context: ExtensionContext) {
        logger.info("🧹 Cleaning up test: ${context.displayName}")
        WebDriverContext.clear()
    }
}