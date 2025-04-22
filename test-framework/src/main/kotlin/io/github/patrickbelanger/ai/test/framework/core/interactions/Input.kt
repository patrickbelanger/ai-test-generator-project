package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Input(by: By): Element(by) {

    fun sendText(text: CharSequence) {
        element.sendKeys(text)
    }
}