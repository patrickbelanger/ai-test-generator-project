package io.github.patrickbelanger.ai.test.framework.handlers

import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.slf4j.LoggerFactory
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ScreenshotOnFailureHandler : TestExecutionExceptionHandler {

    private val logger = LoggerFactory.getLogger(ScreenshotOnFailureHandler::class.java)

    override fun handleTestExecutionException(context: ExtensionContext, throwable: Throwable) {
        logger.error("❌ TEST FAILED: ${context.displayName}", throwable)

        try {
            val webdriver = WebDriverContext.get()
            if (webdriver is TakesScreenshot) {
                val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                val filename = "screenshots/${context.displayName.replace("[^a-zA-Z0-9]".toRegex(), "_")}_$timestamp.png"
                val screenshot = webdriver.getScreenshotAs(OutputType.FILE)
                File(filename).apply {
                    parentFile.mkdirs()
                    screenshot.copyTo(this, overwrite = true)
                }
                logger.info("📸 Screenshot saved to: $filename")
            }
        } catch (e: Exception) {
            logger.warn("⚠️ Failed to take screenshot in exception handler: ${e.message}")
        }

        throw throwable
    }
}