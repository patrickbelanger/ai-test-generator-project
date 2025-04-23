package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

class Checkbox(by: By): ElementsGrouped(by) {

    fun clickAll() {
        elements.forEach { e -> e.click() }
    }
}