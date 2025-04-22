package io.github.patrickbelanger.ai.test.framework.dsl

import io.github.patrickbelanger.ai.test.framework.core.pages.PageObject

inline fun <reified T : PageObject<T>> page(block: T.() -> Unit) {
    val page = T::class.java.getDeclaredConstructor().newInstance()
    page.block()
}
