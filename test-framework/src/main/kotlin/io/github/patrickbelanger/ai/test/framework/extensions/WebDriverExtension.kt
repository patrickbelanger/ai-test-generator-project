package io.github.patrickbelanger.ai.test.framework.extensions

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

fun WebDriver.find(by: By): WebElement {
    return this.findElement(by)
}