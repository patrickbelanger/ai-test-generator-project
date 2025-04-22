package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Checkbox(by: By): Element(by) {

    fun click(value: String) {
        elements.first { e -> e.getAttribute("value")?.lowercase().equals(value.lowercase()) }.click()
    }

    fun clickAll() {
        elements.forEach { e -> e.click() }
    }
}