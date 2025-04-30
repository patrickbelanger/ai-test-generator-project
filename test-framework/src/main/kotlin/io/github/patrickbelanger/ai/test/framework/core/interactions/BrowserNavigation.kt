package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

class BrowserNavigation : WebInterfacesWrapper()  {
    private val logger: Logger = LoggerFactory.getLogger(BrowserNavigation::class.java)

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

    fun wait(duration: Duration, reason: String? = "") {
        logger.info("⏳ Waiting ${duration.toMillis()}ms: $reason")
        runCatching {
            WebDriverWait(webDriver, duration).until {
                Thread.sleep(duration.toMillis())
                true
            }
        }.onSuccess {
            logger.info("✅ Wait completed: $reason")
        }.onFailure { error ->
            logger.info("❌ Wait failed: $reason — ${error.message}")
            throw error
        }
    }

    class Cookies(private val webDriver: WebDriver) {
        private val logger: Logger = LoggerFactory.getLogger(Cookies::class.java) // TODO: To extract

        fun get(cookie: Cookie): Cookie? {
            return webDriver.manage().cookies.firstOrNull { it.name == cookie.name }
        }

        fun get(name: String): Cookie? {
            return webDriver.manage().getCookieNamed(name)
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

        fun waitForCookie(cookie: Cookie, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(cookie) !== null
                }
            }.onSuccess {
                logger.info("✅ Wait completed")
            }.onFailure { error ->
                logger.info("❌ Wait failed — ${error.message}")
                throw error
            }
        }

        fun waitForCookie(name: String, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(name) !== null
                }
            }.onSuccess {
                logger.info("✅ Wait completed")
            }.onFailure { error ->
                logger.info("❌ Wait failed — ${error.message}")
                throw error
            }
        }

        fun waitForDeletedCookie(cookie: Cookie, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(cookie) == null
                }
            }.onSuccess {
                logger.info("✅ Wait completed")
            }.onFailure { error ->
                logger.info("❌ Wait failed — ${error.message}")
                throw error
            }
        }

        fun waitForDeletedCookie(name: String, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(name) == null
                }
            }.onSuccess {
                logger.info("✅ Wait completed")
            }.onFailure { error ->
                logger.info("❌ Wait failed — ${error.message}")
                throw error
            }
        }

        fun waitForNoCookies(timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    getAll().size == 0
                }
            }.onSuccess {
                logger.info("✅ Wait completed")
            }.onFailure { error ->
                logger.info("❌ Wait failed — ${error.message}")
                throw error
            }
        }
    }

    open class Frames : WebInterfacesWrapper() {
        private val logger: Logger = LoggerFactory.getLogger(Frames::class.java) // TODO: To extract

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
