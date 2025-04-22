package io.github.patrickbelanger.ai.test.framework.demo.saucedemo.pageobjects

import io.github.patrickbelanger.ai.test.framework.core.interactions.Button
import io.github.patrickbelanger.ai.test.framework.core.interactions.Input
import io.github.patrickbelanger.ai.test.framework.core.pages.PageObject
import org.openqa.selenium.By

class LoginPage : PageObject<LoginPage>() {

    private val usernameField = Input(By.id("user-name"))
    private val passwordField = Input(By.id("password"))
    private val loginButton = Button(By.id("login-button"))

    override fun navigateTo() {
        webdriver.get("https://www.saucedemo.com/")
    }

    fun setUsername(username: String): LoginPage {
        usernameField.sendText(username)
        return this
    }

    fun setPassword(password: String): LoginPage {
        passwordField.sendText(password)
        return this
    }

    fun clickLogin() {
        loginButton.click()
    }

    fun fill(username: String, password: String) {
        setUsername(username)
        .setPassword(password)
        .clickLogin()
    }
}