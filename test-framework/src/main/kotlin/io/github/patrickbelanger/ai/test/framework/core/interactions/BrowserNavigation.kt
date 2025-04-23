package io.github.patrickbelanger.ai.test.framework.core.interactions

import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver

class BrowserNavigation {

    private val webdriver: WebDriver
        get() = WebDriverContext.get()

    fun cookies(): Cookies {
        return Cookies(webdriver)
    }

    fun currentUrl(): String? {
        return webdriver.currentUrl
    }

    fun navigateTo(url: String) {
        webdriver.get(url)
    }

    fun title(): String? {
        return webdriver.title
    }

    class Cookies(val webdriver: WebDriver) {

        fun get(name: String) {
            webdriver.manage().getCookieNamed(name)
        }

        fun getAll(): MutableSet<Cookie> {
            return webdriver.manage().cookies
        }

        fun add(cookie: Cookie) {
            webdriver.manage().addCookie(cookie)
        }

        fun delete(cookie: Cookie) {
            webdriver.manage().deleteCookie(cookie)
        }

        fun deleteAll() {
            webdriver.manage().deleteAllCookies()
        }
    }
}
