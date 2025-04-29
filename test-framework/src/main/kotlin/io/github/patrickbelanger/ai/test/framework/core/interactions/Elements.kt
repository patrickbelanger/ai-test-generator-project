package io.github.patrickbelanger.ai.test.framework.core.interactions

import org.openqa.selenium.By

abstract class Elements: WebInterfacesWrapper() {
    fun button(by: By) = Button(by)
    fun browser() = BrowserNavigation()
    fun input(by: By) = Input(by)
    fun checkbox(by: By) = Checkbox(by)
    fun dropdown(by: By) = Dropdown(by)
    fun radio(by: By) = Radio(by)
}