package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openqa.selenium.Cookie

class BrowserNavigationTest : FrameworkBase() {

    val targetUrl = "https://www.google.com"
    val expectedTitle = "Google"

    @Test
    fun shouldBeAbleToClickOnASpecificCheckbox() {
        browser().navigateTo(targetUrl)
        browser().currentUrl()?.let { assertTrue(it.contains(targetUrl)) }
        browser().title()?.let { assertEquals(it, expectedTitle) }
    }

    @Test
    fun shouldBeAbleToAddACookieOnADomainCookieIsValidFor() {
       val cookie = Cookie("new-cookie", expectedTitle)
       browser().navigateTo(targetUrl)
       browser().cookies().add(cookie)
       assertTrue(browser().cookies().getAll().size > 0)
       assertTrue(browser().cookies().getAll().contains(cookie))
    }

    @Test
    fun shouldBeAbleToDeleteACookie() {
        val cookie = Cookie("working-cookie", expectedTitle)
        browser().navigateTo(targetUrl)
        browser().cookies().add(cookie)

        val cookiesCount = browser().cookies().getAll().size
        browser().cookies().delete(cookie)
        assertEquals(cookiesCount - 1, browser().cookies().getAll().size)
    }

    @Test
    fun shouldBeAbleToDeleteAllCookies() {
        browser().navigateTo(targetUrl)
        browser().cookies().deleteAll()
        assertEquals(0, browser().cookies().getAll().size)
    }
}