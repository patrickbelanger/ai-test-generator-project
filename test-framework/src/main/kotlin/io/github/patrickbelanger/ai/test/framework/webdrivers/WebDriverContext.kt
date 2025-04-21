package io.github.patrickbelanger.ai.test.framework.webdrivers

import org.openqa.selenium.WebDriver

object WebDriverContext {
    private val threadLocalDriver = ThreadLocal<WebDriver>()

    fun set(driver: WebDriver) = threadLocalDriver.set(driver)
    fun get(): WebDriver = threadLocalDriver.get()
    fun clear(): () -> Unit = {
        if (threadLocalDriver.get() !== null) {
            threadLocalDriver.get().quit()
            threadLocalDriver.remove()
        }
    }
}