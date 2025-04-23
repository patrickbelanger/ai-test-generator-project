package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

abstract class Elements {
    fun button(by: By) = Button(by)
    fun input(by: By) = Input(by)
    fun checkbox(by: By) = Checkbox(by)
    fun dropdown(by: By) = Dropdown(by)
    fun radio(by: By) = Radio(by)
}