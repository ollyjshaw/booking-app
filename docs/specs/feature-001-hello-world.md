# Feature 001: Javalin hello world

## What to build

Set up a runnable Javalin 7.2.2 application written in Kotlin. The application must expose a single Hypertext Transfer Protocol (HTTP) GET endpoint at the path `/hello`. That endpoint must return a 200 OK status with the plain text body `Hello World`. Write one automated test that verifies this behaviour using Javalin's built-in test tooling, JavalinTest.

Provide a Makefile with at least `build` and `test` targets. The Makefile is the single entry point a developer uses to build and test the project. It may delegate to whichever underlying build tool the project adopts. The choice of build tool is not decided at this stage, so the Makefile targets must remain the stable interface regardless of what sits beneath them.

Create a project README as part of this feature. The README must document how to build and test the project using those Makefile targets.

## Out of scope

- Any domain concepts (bookings, users, resources)
- Any database or persistence layer
- Any authentication or authorisation
- JSON or Hypertext Markup Language (HTML) response formats
- Multiple endpoints
- Environment configuration or packaging

## Acceptance criteria

1. The application starts without error.
2. A GET request to `/hello` returns HTTP 200.
3. The response body is the plain text string `Hello World`, with no additional formatting, wrapping, or content negotiation.
4. The response Content-Type is `text/plain`.
5. A single automated test covers the above, using JavalinTest against a real server instance.
6. The test passes with no skips or warnings in a clean build.
7. A Makefile exists at the repository root. Running `make build` compiles the project. Running `make test` compiles the project and runs the automated test suite.
8. A README exists at the repository root. It documents how to build and test the project using `make build` and `make test`.
