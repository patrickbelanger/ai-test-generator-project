package io.github.patrickbelanger.ai.test.framework.watchers

import org.junit.jupiter.api.extension.*
import org.slf4j.LoggerFactory
import java.util.*

class TestResultWatcher : TestWatcher, BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private val logger = LoggerFactory.getLogger(TestResultWatcher::class.java)

    override fun testSuccessful(context: ExtensionContext) {
        logger.info("✅ TEST PASSED: ${context.displayName}")
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable) {
        logger.error("❌ TEST FAILED: ${context.displayName}", cause)
    }

    override fun testAborted(context: ExtensionContext, cause: Throwable) {
        logger.warn("⚠️ TEST ABORTED: ${context.displayName}", cause)
    }

    override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
        logger.info("🚫 TEST DISABLED: ${context.displayName} – Reason: ${reason.orElse("N/A")}")
    }

    override fun beforeTestExecution(context: ExtensionContext) {
        logger.info("🧪 Starting test: ${context.displayName}")
    }

    override fun afterTestExecution(context: ExtensionContext) {
        logger.info("🧹 Finished test: ${context.displayName}")
    }
}