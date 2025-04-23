package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

abstract class ElementsGrouped(by: By): AbstractElement(by) {

    fun click(value: String) {
        elements.first { e ->
            e.getAttribute("value")?.lowercase().equals(value.lowercase()) ||
                    e.getAttribute("id")?.lowercase().equals((value.lowercase()))
        }.click()
    }
}