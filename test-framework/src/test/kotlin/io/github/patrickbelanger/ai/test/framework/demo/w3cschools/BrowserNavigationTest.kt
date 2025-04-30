package io.github.patrickbelanger.ai.test.framework.demo.w3cschools

import io.github.patrickbelanger.ai.test.framework.core.FrameworkBase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.Cookie
import java.util.*

class BrowserNavigationTest : FrameworkBase() {
    val targetUrl = "https://www.google.com"
    val targetUrlFrame = "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_checkbox"
    val expectedTitle = "Google"

    @Test
    fun `Should be able to click on a specific checkbox`() {
        browser().navigateTo(targetUrl)
        browser().currentUrl()?.let { assertTrue(it.contains(targetUrl)) }
        browser().title()?.let { assertEquals(it, expectedTitle) }
    }

    @Test
    fun `Should be able to add a cookie on a domain cookie is valid for`() {
       val cookie = Cookie("new-cookie", expectedTitle)
       browser().navigateTo(targetUrl)
       browser().cookies().add(cookie)
       assertTrue(browser().cookies().getAll().size > 0)
       assertTrue(browser().cookies().getAll().contains(cookie))
    }

    @Test
    fun `Should be able to delete a cookie`() {
        val cookie = Cookie("working-cookie-${UUID.randomUUID()}", expectedTitle)
        browser().navigateTo(targetUrl)
        browser().cookies().add(cookie)

        val cookiesCount = browser().cookies().getAll().size
        browser().cookies().waitForCookie(cookie)

        browser().cookies().delete(cookie)
        browser().cookies().waitForDeletedCookie(cookie)
        assertEquals(cookiesCount - 1, browser().cookies().getAll().size)
    }

    @Test
    fun `Should be able to delete all cookies`() {
        val cookie = Cookie("working-cookie-${UUID.randomUUID()}", expectedTitle)
        browser().navigateTo(targetUrl)
        browser().cookies().add(cookie)
        browser().cookies().waitForCookie(cookie)

        browser().cookies().deleteAll()
        browser().cookies().waitForNoCookies()
        assertEquals(0, browser().cookies().getAll().size)
    }

    @Test
    fun `Should be able to switch frame by using By locator`() {
        browser().navigateTo(targetUrlFrame)
        browser().frames().switchTo(By.id("iframeResult"))
        assertTrue(webDriver.findElements(By.xpath("//input[@type='checkbox']")).size == 3)
    }

    @Test
    fun `Should be able to switch frame by using String locator`() {
        browser().navigateTo(targetUrlFrame)
        browser().frames().switchTo("iframeResult")
        assertTrue(webDriver.findElements(By.xpath("//input[@type='checkbox']")).size == 3)
    }
}