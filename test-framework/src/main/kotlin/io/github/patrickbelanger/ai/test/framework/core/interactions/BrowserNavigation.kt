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
            runCatching {
                webDriver.manage().addCookie(cookie)
            }.onSuccess {
                logger.info("✅ Add cookie - cookie {} - passed", cookie)
            }.onFailure { error ->
                logger.info("❌ Add cookie - cookies {} - failed {}", cookie, error.message)
                throw error
            }
        }

        fun delete(cookie: Cookie) {
            runCatching {
                webDriver.manage().deleteCookie(cookie)
            }.onSuccess {
                logger.info("✅ Delete cookie - cookie {} - passed", cookie)
            }.onFailure { error ->
                logger.info("❌ Delete cookie - cookies {} - failed {}", cookie, error.message)
                throw error
            }
        }

        fun deleteAll() {
            runCatching {
                webDriver.manage().deleteAllCookies()
            }.onSuccess {
                logger.info("✅ Delete all cookies - passed")
            }.onFailure { error ->
                logger.info("❌ Delete all cookies - failed {}", error.message)
                throw error
            }
        }

        fun waitForCookie(cookie: Cookie, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(cookie) !== null
                }
            }.onSuccess {
                logger.info("✅ Wait for cookie - cookie {} - passed", cookie.toString())
            }.onFailure { error ->
                logger.info("❌ Wait for cookie - cookie {} failed — {}", cookie.toString(), error.message)
                throw error
            }
        }

        fun waitForCookie(name: String, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(name) !== null
                }
            }.onSuccess {
                logger.info("✅ Wait for cookie - name {} - passed", name)
            }.onFailure { error ->
                logger.info("❌ Wait for cookie - name {} failed — {}", name, error.message)
                throw error
            }
        }

        fun waitForDeletedCookie(cookie: Cookie, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(cookie) == null
                }
            }.onSuccess {
                logger.info("✅ Wait for cookie to be deleted - cookie {} - passed", cookie.toString())
            }.onFailure { error ->
                logger.info("❌ Wait for cookie to be deleted - cookie {} failed — {}", cookie.toString(), error.message)
                throw error
            }
        }

        fun waitForDeletedCookie(name: String, timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    get(name) == null
                }
            }.onSuccess {
                logger.info("✅ Wait for cookie to be deleted - name {} - passed", name)
            }.onFailure { error ->
                logger.info("❌ Wait for cookie to be deleted - name {} failed — {}", name, error.message)
                throw error
            }
        }

        fun waitForNoCookies(timeout: Duration = Duration.ofSeconds(2)) {
            runCatching {
                WebDriverWait(webDriver, timeout).until {
                    getAll().size == 0
                }
            }.onSuccess {
                logger.info("✅ Wait for all cookies to be deleted - passed")
            }.onFailure { error ->
                logger.info("❌ Wait for all cookies to be deleted - failed — {}", error.message)
                throw error
            }
        }
    }

    open class Frames : WebInterfacesWrapper() {
        private val logger: Logger = LoggerFactory.getLogger(Frames::class.java) // TODO: To extract

        fun switchTo(by: By) {
            runCatching {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by))
            }.onSuccess {
                logger.info("✅ Wait for all cookies to be deleted - passed")
            }.onFailure { error ->
                logger.info("❌ Wait for all cookies to be deleted - failed — {}", error.message)
                throw error
            }
        }

        fun switchTo(index: Int) {
            runCatching {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index))
            }.onSuccess {
                logger.info("✅ Switch frame - index {} - passed", index)
            }.onFailure { error ->
                logger.info("❌ Switch frame - index {} - failed — {}", index, error.message)
                throw error
            }
        }

        fun switchTo(nameOrId: String) {
            runCatching {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId))
            }.onSuccess {
                logger.info("✅ Switch frame - name/id {} - passed", nameOrId)
            }.onFailure { error ->
                logger.info("❌ Switch frame - name/id {} - failed — {}", nameOrId, error.message)
                throw error
            }
        }

        fun switchTo(webElement: WebElement) {
            runCatching {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement))
            }.onSuccess {
                logger.info("✅ Switch frame - webElement {} - passed", webElement.toString())
            }.onFailure { error ->
                logger.info("❌ Switch frame - webElement {} - failed — {}", webElement.toString(), error.message)
                throw error
            }
        }
    }
}
