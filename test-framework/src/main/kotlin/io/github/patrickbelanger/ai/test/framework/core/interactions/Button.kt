package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Button (by: By): ElementWrapper(by) {
    fun isEnabled(): Boolean {
        return findElement().isEnabled
    }

    fun click() {
        element.click()
    }
}