package io.github.patrickbelanger.ai.test.framework.core

import io.github.patrickbelanger.ai.test.framework.extensions.SeleniumJunitExtension
import io.github.patrickbelanger.ai.test.framework.handlers.ScreenshotOnFailureHandler
import io.github.patrickbelanger.ai.test.framework.watchers.TestResultWatcher
import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(
    SpringExtension::class,
    SeleniumJunitExtension::class,
    ScreenshotOnFailureHandler::class,
    TestResultWatcher::class
)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class FrameworkBase {

    private val logger: Logger = LoggerFactory.getLogger(FrameworkBase::class.java)

    @BeforeEach
    fun setUp(testInfo: TestInfo) {
        logger.info("🚀 Prepare to launch test: ${testInfo.displayName}")
    }

    @AfterEach
    fun tearDown(testInfo: TestInfo) {
        logger.info("🧹 Cleaning up test: ${testInfo.displayName}")
        WebDriverContext.remove()
    }
}