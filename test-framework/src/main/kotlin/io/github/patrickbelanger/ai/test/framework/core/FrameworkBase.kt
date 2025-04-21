package io.github.patrickbelanger.ai.test.framework.core

import io.github.patrickbelanger.ai.test.framework.extensions.SeleniumJunitExtension
import io.github.patrickbelanger.ai.test.framework.handlers.ScreenshotOnFailureHandler
import io.github.patrickbelanger.ai.test.framework.watchers.TestResultWatcher
import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverFactory
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ExtendWith(SeleniumJunitExtension::class, ScreenshotOnFailureHandler::class, TestResultWatcher::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class FrameworkBase {

    @Autowired
    protected lateinit var webdriver: WebDriver

    @Autowired
    private lateinit var webDriverFactory: WebDriverFactory

    private val logger: Logger = LoggerFactory.getLogger(FrameworkBase::class.java)

    @BeforeEach
    fun setUp(testInfo: TestInfo) {
        logger.info("🚀 Prepare to launch test: ${testInfo.displayName}")
        webDriverFactory.getWebDriver()
    }

    @AfterEach
    fun tearDown(testInfo: TestInfo) {
        logger.info("🧹 Cleaning up test: ${testInfo.displayName}")
        webDriverFactory.quitWebDriver()
    }
}