package app

import io.javalin.Javalin

fun createApp(): Javalin = Javalin.create { config ->
    config.routes.get("/") { ctx -> ctx.contentType("text/plain").result("Hello World") }
    config.routes.get("/hello") { ctx -> ctx.contentType("text/plain").result("Hello World") }
}
