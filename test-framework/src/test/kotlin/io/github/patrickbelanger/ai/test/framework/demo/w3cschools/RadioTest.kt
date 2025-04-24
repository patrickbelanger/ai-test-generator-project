package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects.TryItPage
import io.github.patrickbelanger.ai.test.framework.dsl.page
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class RadioTest : FrameworkBase() {

    @Test
    fun shouldBeAbleToClickOnASpecificRadio() {
        page<TryItPage> {
            navigateTo("tryhtml5_input_type_radio")
            switchFrame()
            radio(By.xpath("//input[@type='radio']")).click("CSS")
        }
    }
}