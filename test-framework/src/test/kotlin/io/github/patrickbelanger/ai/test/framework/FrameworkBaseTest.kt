package io.github.patrickbelanger.ai.test.framework

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.By
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FrameworkBaseTest: FrameworkBase() {

    @Test
    @Order(1)
    fun shouldLoginSuccessfullyUsingPlainSeleniumWebDriverCombinedWithSpringBoot() {
        webdriver.get("https://www.saucedemo.com/")
        webdriver.findElement(By.id("user-name")).sendKeys("standard_user")
        webdriver.findElement(By.id("password")).sendKeys("secret_sauce")
        webdriver.findElement(By.id("login-button")).click()
        println("Page Title: ${webdriver.title}")
    }
}