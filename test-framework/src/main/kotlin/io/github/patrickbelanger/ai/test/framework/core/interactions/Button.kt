package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Button (by: By): ElementWrapper(by) {
    private val logger: Logger = LoggerFactory.getLogger(Button::class.java)

    fun isEnabled(): Boolean {
        return findElement().isEnabled
    }

    fun click() {
        runCatching {
            element.click()
        }.onSuccess {
            logger.info("✅ Button click - locator {} - passed", by)
        }.onFailure { error ->
            logger.info("❌ Button click - locator {} - failed {}", by, error.message)
            throw error
        }
    }
}