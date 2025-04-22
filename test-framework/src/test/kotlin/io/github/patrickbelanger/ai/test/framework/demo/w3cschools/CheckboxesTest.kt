package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.core.interactions.Checkbox
import io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects.TryItPage
import io.github.patrickbelanger.ai.test.framework.dsl.page
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class CheckboxesTest : FrameworkBase() {

    @Test
    fun shouldBeAbleToClickOnASpecificCheckbox() {
        page<TryItPage> {
            navigateTo()
            switchFrame()
            Checkbox(By.xpath("//input[@type='checkbox']")).click("Car")
        }
    }

    @Test
    fun shouldBeAbleToClickOnAEveryCheckboxes() {
        page<TryItPage> {
            navigateTo()
            switchFrame()
            Checkbox(By.xpath("//input[@type='checkbox']")).clickAll()
        }
    }
}