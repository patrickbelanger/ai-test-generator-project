package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Dropdown(by: By) : ElementWrapper(by) {
    private val logger: Logger = LoggerFactory.getLogger(Dropdown::class.java)

    fun isEnabled(): Boolean {
        return findElement().isEnabled
    }

    fun isMultiple(): Boolean {
        return Select(element).isMultiple
    }

    fun selectByIndex(index: Int) {
        runCatching {
            Select(element).selectByIndex(index)
        }.onSuccess {
            logger.info("✅ Select - by index {} - Passed", index)
        }.onFailure { error ->
            logger.info("❌ Select - by index {} - Failed {}", index, error.message)
            throw error
        }

    }

    fun selectByText(text: String) {
        runCatching {
            Select(element).selectByVisibleText(text)
        }.onSuccess {
            logger.info("✅ Select - by text {} - Passed", text)
        }.onFailure { error ->
            logger.info("❌ Select - by text {} - Failed {}", text, error.message)
            throw error
        }
    }

    fun selectByValue(value: String) {
        runCatching {
            Select(element).selectByValue(value)
        }.onSuccess {
            logger.info("✅ Select - by value {} - Passed", value)
        }.onFailure { error ->
            logger.info("❌ Select - by value {} - Failed {}", value, error.message)
            throw error
        }
    }
}