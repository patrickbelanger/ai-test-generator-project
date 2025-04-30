package io.github.patrickbelanger.ai.test.framework.core.interactions

import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class WebInterfacesWrapper {
    open val webDriver: WebDriver
        get() = WebDriverContext.get()

    val wait: WebDriverWait
        get() = WebDriverWait(webDriver, Duration.ofSeconds(10))
}