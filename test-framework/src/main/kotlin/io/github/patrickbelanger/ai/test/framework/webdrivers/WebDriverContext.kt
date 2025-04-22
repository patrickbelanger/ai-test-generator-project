package io.github.patrickbelanger.ai.test.framework.webdrivers

import org.openqa.selenium.WebDriver
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object WebDriverContext {

    private val logger : Logger = LoggerFactory.getLogger(WebDriverContext::class.java)
    private val context: ThreadLocal<WebDriver> = ThreadLocal()

    fun set(driver: WebDriver) {
        logger.info("🆕 Set WebDriver for thread: ${Thread.currentThread().name}")
        context.set(driver)
    }

    fun get(): WebDriver {
        return context.get() ?: throw IllegalStateException("WebDriver is not set in WebDriverContext for this thread.")
    }

    fun remove() {
        logger.info("🗑️ Remove WebDriver for thread: ${Thread.currentThread().name}")
        context.get().quit()
        context.remove()
    }

    fun isInitialized(): Boolean {
        return context.get() != null
    }
}