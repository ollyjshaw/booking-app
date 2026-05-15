package app

import io.javalin.testtools.JavalinTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HelloTest {
    @Test
    fun `GET hello returns 200 with plain text Hello World`() {
        JavalinTest.test(createApp()) { _, client ->
            val response = client.get("/hello")
            assertEquals(200, response.code)
            assertEquals("Hello World", response.body?.string())
            assertTrue(response.headers().get("Content-Type")?.contains("text/plain") == true)
        }
    }

    @Test
    fun `GET root returns 200 with plain text Hello World`() {
        JavalinTest.test(createApp()) { _, client ->
            val response = client.get("/")
            assertEquals(200, response.code)
            assertEquals("Hello World", response.body?.string())
            assertTrue(response.headers().get("Content-Type")?.contains("text/plain") == true)
        }
    }
}
