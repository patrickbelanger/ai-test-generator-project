package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects.TryItPage
import io.github.patrickbelanger.ai.test.framework.dsl.page
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class CheckboxesTest : FrameworkBase() {

    @Test
    fun `Should be able to click on a specific checkbox`() {
        page<TryItPage> {
            navigateTo()
            switchFrame()
            checkbox(By.xpath("//input[@type='checkbox']")).click("Car")
        }
    }

    @Test
    fun `Should be able to click on a every checkboxes`() {
        page<TryItPage> {
            navigateTo()
            switchFrame()
            checkbox(By.xpath("//input[@type='checkbox']")).clickAll()
        }
    }
}