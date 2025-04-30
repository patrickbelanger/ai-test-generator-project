package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Checkbox(by: By): ElementsGrouped(by) {
    fun isChecked() {
        element.isSelected // Don't be confused by the method name -> https://www.selenium.dev/documentation/webdriver/elements/information/
    }

    fun clickAll() {
        elements.forEach { e -> e.click() }
    }
}