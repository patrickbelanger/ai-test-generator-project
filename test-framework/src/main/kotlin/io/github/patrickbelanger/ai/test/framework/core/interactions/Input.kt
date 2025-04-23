package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Input(by: By): AbstractElement(by) {

    fun sendText(text: CharSequence) {
        element.sendKeys(text)
    }
}