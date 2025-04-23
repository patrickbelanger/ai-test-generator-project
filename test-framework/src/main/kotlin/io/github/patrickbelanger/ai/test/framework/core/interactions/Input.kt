package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Input(by: By): ElementWrapper(by) {

    fun clear() {
        element.clear()
    }

    fun isEnabled(): Boolean {
        return findElement().isEnabled
    }

    fun sendText(text: CharSequence, clear: Boolean = false) {
        if (clear) {
            element.clear()
        }
        element.sendKeys(text)
    }
}