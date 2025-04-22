package io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects

import io.github.patrickbelanger.ai.test.framework.core.pages.PageObject

class TryItPage : PageObject<TryItPage>() {

    override fun navigateTo() {
        webdriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox")
    }

    fun navigateTo(filename: String) {
        webdriver.get("https://www.w3schools.com/tags/tryit.asp?filename=$filename")
    }

    fun switchFrame() {
        webdriver.switchTo().frame("iframeResult") // To debug
        //Browser(By.name("iframeResult")).switchFrame()
    }
}