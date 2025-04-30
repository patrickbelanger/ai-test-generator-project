package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select

class Dropdown(by: By) : ElementWrapper(by) {
    fun isEnabled(): Boolean {
        return findElement().isEnabled
    }

    fun isMultiple(): Boolean {
        return Select(element).isMultiple
    }

    fun selectByIndex(index: Int) {
        Select(element).selectByIndex(index)
    }

    fun selectByText(value: String) {
        Select(element).selectByVisibleText(value)
    }

    fun selectByValue(value: String) {
        Select(element).selectByValue(value)
    }
}