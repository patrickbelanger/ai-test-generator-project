package io.github.patrickbelanger.ai.test.framework.core.pages

import io.github.patrickbelanger.ai.test.framework.core.interactions.Elements
import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.openqa.selenium.WebDriver

abstract class PageObject<T : PageObject<T>> : Elements() {

    protected val webdriver: WebDriver
        get() = WebDriverContext.get()

    abstract fun navigateTo()
    open fun currentUrl(): String? = webdriver.currentUrl
    open fun title(): String? = webdriver.title
}