package io.github.patrickbelanger.ai.test.framework.core.interactions

import io.github.patrickbelanger.ai.test.framework.webdrivers.WebDriverContext
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class AbstractElement(protected val by: By) {

    protected val webdriver: WebDriver
        get() = WebDriverContext.get()

    protected val wait: WebDriverWait
        get() = WebDriverWait(webdriver, Duration.ofSeconds(10))

    /* TODO: Improve element synchronization logic to reduce flakiness and enhance test reliability. For now, this implementation will suffice; revisit later for optimization */
    protected val element: WebElement
        get() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by))
        }

    protected val elements: List<WebElement>
        get() {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
        }
}