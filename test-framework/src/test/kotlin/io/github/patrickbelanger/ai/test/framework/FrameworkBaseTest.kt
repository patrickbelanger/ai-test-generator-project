package io.github.patrickbelanger.ai.test.framework

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.extensions.find
import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class FrameworkBaseTest: FrameworkBase() {

    @Test
    fun shouldLoginSuccessfullyUsingPlainSeleniumWebDriverCombinedWithSpringBoot() {
        with(WebDriverContext.get()) {
            get("https://www.saucedemo.com/")
            find(By.id("user-name")).sendKeys("standard_user")
            find(By.id("password")).sendKeys("secret_sauce")
            find(By.id("login-button")).click()
            assertEquals("Swag Labs", title)
        }
    }

    @Test
    fun shouldLoginFailsDueToWrongElementId() {
        with(WebDriverContext.get()) {
            get("https://www.saucedemo.com/")
            assertThrows(org.openqa.selenium.NoSuchElementException::class.java) {
                find(By.id("user1name")).sendKeys("standard_user")
            }
        }
    }
}