package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class ElementWrapper(
    protected val by: By = By.id(""),
    protected val nameOrId: String? = null,
    protected val index: Int? = null,
    protected val webElement: WebElement? = null
) : WebInterfacesWrapper() {
    /* TODO: Improve element synchronization logic to reduce flakiness and enhance test reliability. For now, this implementation will suffice; revisit later for optimization */
    protected val element: WebElement
        get() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by))
        }

    protected val elements: List<WebElement>
        get() {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
        }

    protected fun findElement(): WebElement =
        WebDriverWait(webDriver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfElementLocated(by))

    fun isDisplayed(): Boolean {
        return findElement().isDisplayed
    }
}