package io.github.patrickbelanger.ai.test.framework.demo.saucedemo

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.demo.saucedemo.pageobjects.LoginPage
import io.github.patrickbelanger.ai.test.framework.dsl.page
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SwagLabsDemoTest : FrameworkBase() {

    @Test
    fun shouldLoginSuccessfullyUsingPageObjectModelCombinedWithKotlinDsl() {
        page<LoginPage> {
            navigateTo()
            fill("standard_user", "secret_sauce")
            assertEquals("Swag Labs", title())
        }
    }
}