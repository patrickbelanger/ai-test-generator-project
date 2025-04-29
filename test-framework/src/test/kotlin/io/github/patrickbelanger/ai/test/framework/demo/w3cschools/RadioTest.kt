package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects.TryItPage
import io.github.patrickbelanger.ai.test.framework.dsl.page
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class RadioTest : FrameworkBase() {

    @Test
    fun `Should be able to click on a specific radio`() {
        page<TryItPage> {
            navigateTo("tryhtml5_input_type_radio")
            switchFrame()
            radio(By.xpath("//input[@type='radio']")).click("CSS")
        }
    }
}