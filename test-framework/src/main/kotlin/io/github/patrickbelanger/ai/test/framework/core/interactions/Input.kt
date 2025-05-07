package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Input(by: By): ElementWrapper(by) {
    private val logger: Logger = LoggerFactory.getLogger(Input::class.java)

    fun clear() {
        runCatching {
            element.clear()
        }.onSuccess {
            logger.info("✅ Clear text - Passed")
        }.onFailure { error ->
            logger.info("❌ Clear text - Failed {}", error.message)
            throw error
        }
    }

    fun isEnabled(): Boolean {
        return findElement().isEnabled
    }

    fun sendText(text: CharSequence, clear: Boolean = false) {
        runCatching {
            if (clear) {
                element.clear()
            }
            element.sendKeys(text)
        }.onSuccess {
            logger.info("✅ Send text - value: {} - Passed", text)
        }.onFailure { error ->
            logger.info("❌ Send text - value: {} - Failed {}", text, error.message)
            throw error
        }
    }
}