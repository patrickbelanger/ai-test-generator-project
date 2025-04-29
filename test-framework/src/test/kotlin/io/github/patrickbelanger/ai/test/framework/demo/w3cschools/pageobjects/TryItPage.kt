package io.github.patrickbelanger.ai.test.framework.demo.w3cschools.pageobjects

import io.github.patrickbelanger.ai.test.framework.core.pages.PageObject

class TryItPage : PageObject<TryItPage>() {

    override fun navigateTo() {
        browser().navigateTo("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox")
    }

    fun navigateTo(filename: String) {
        browser().navigateTo("https://www.w3schools.com/tags/tryit.asp?filename=$filename")
    }

    fun switchFrame() {
        browser().frames().switchTo("iframeResult")
    }
}