package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import io.github.patrickbelanger.ai.test.framework.core.interactions.Dropdown
import io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects.TryItPage
import io.github.patrickbelanger.ai.test.framework.dsl.page
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class DropdownTest : FrameworkBase() {

    @Test
    fun shouldBeAbleToSelectASpecificValue() {
        page<TryItPage> {
            navigateTo("tryhtml_select")
            switchFrame()
            Dropdown(By.name("cars")).selectByText("Audi")
        }
    }
}