package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions

class BrowserNavigation : WebInterfacesWrapper()  {

    fun cookies(): Cookies {
        return Cookies(webDriver)
    }

    fun currentUrl(): String? {
        return webDriver.currentUrl
    }

    fun frames(): Frames {
        return Frames()
    }

    fun navigateTo(url: String) {
        webDriver.get(url)
    }

    fun title(): String? {
        return webDriver.title
    }

    fun forceWait() {
        wait.until(ExpectedConditions.titleIs(title()))
    }

    class Cookies(private val webDriver: WebDriver) {

        fun get(name: String) {
            webDriver.manage().getCookieNamed(name)
        }

        fun getAll(): MutableSet<Cookie> {
            return webDriver.manage().cookies
        }

        fun add(cookie: Cookie) {
            webDriver.manage().addCookie(cookie)
        }

        fun delete(cookie: Cookie) {
            webDriver.manage().deleteCookie(cookie)
        }

        fun deleteAll() {
            webDriver.manage().deleteAllCookies()
        }
    }

    open class Frames : WebInterfacesWrapper() {

        fun switchTo(by: By) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by))
        }

        fun switchTo(index: Int) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index))
        }

        fun switchTo(nameOrId: String) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId))
        }

        fun switchTo(webElement: WebElement) {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement))
        }
    }
}
