package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Button(by: By): Element(by) {

    fun click() {
        element.click()
    }
}