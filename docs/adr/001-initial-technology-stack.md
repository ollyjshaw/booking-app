# ADR 001: Initial Technology Stack

Date: 2026-05-15
Status: Accepted
Deciders: architect-system, ollyjshaw@gmail.com

---

## Context

We are building a greenfield booking system in Kotlin. Before any implementation begins, we must agree the core technology stack. The choices made here will establish the patterns others will copy and affect every layer of the system.

The team's primary goals are fast flow and simplicity over framework power. Specifically:

- Controller-level tests must fire real HTTP without complex mock setup.
- The frontend must not require a separate JavaScript build pipeline.
- The system must persist bookings to a relational database.
- The stack should favour minimal concepts and low ceremony.

All decisions below were evaluated against these goals.

---

## Options considered

### Language

| Option | Summary | Why rejected |
|--------|---------|--------------|
| Java | JVM language; runs anywhere Kotlin does | More verbose than Kotlin; lacks null safety, data classes, and extension functions; Kotlin is a strict improvement for new JVM projects |
| **Kotlin** | JVM language from JetBrains; concise, null-safe, interoperable with Java | **Chosen** — see Decision below |

### Web framework

| Option | Summary | Why rejected |
|--------|---------|--------------|
| Spring Boot | Full-featured JVM framework with extensive ecosystem | Too much magic and ceremony; auto-configuration obscures what the system is doing; heavier startup cost |
| Ktor | Kotlin-native async framework from JetBrains | More complex concurrency model than needed; smaller ecosystem; less familiar to the team |
| **Javalin** | Lightweight Kotlin/Java framework; FastAPI-like routing; runs on Jetty | **Chosen** — see Decision below |

### ORM and database access

| Option | Summary | Why rejected |
|--------|---------|--------------|
| Spring Data JPA / Hibernate | Annotation-based ORM; full JPA spec | Annotation magic; verbose entity model; tightly coupled to Spring |
| jOOQ | Typesafe SQL DSL; code-generated from schema | Code generation step adds friction; more setup than needed for this system's scale |
| **Exposed** | JetBrains Kotlin ORM; Kotlin DSL approach | **Chosen** — see Decision below |

### Database

| Option | Summary | Why rejected |
|--------|---------|--------------|
| PostgreSQL only | Single database for all environments | Slower test cycles; requires a running Postgres instance for local dev and CI |
| **H2 (dev/test) + PostgreSQL (production)** | In-memory database for fast local iteration; real database in production | **Chosen** — see Decision below |

### Frontend

| Option | Summary | Why rejected |
|--------|---------|--------------|
| React or Vue SPA | Client-side rendering with a separate frontend build pipeline | Introduces a JS build pipeline and a second deployment artefact; adds complexity with no benefit for this use case |
| Plain server-rendered HTML (no enhancement) | Thymeleaf templates only | Acceptable, but HTMX adds progressive interactivity with no JS build step |
| **Thymeleaf + HTMX** | Server-rendered HTML templates progressively enhanced with HTMX attributes | **Chosen** — see Decision below |

### Testing approach

| Option | Summary | Why rejected |
|--------|---------|--------------|
| MockMvc / RestAssured with mocked services | Mock the HTTP layer; test handlers in isolation | Mocking at the HTTP layer hides integration issues; requires significant test setup |
| **JUnit 5 + Javalin TestTools** | JUnit 5 for unit tests; Javalin TestTools for controller-level tests against a real in-process server | **Chosen** — see Decision below |

### API documentation

| Option | Summary | Why rejected |
|--------|---------|--------------|
| Springdoc / manual OpenAPI YAML | Third-party OpenAPI generation or hand-authored spec | Adds a separate dependency or manual maintenance burden |
| **Javalin OpenAPI plugin + Swagger UI** | Built-in OpenAPI support with Swagger UI served from the app | **Chosen** — see Decision below |

---

## Decision

We will write all application code in Kotlin. We will build the booking system on Javalin (HTTP framework) with Exposed (Kotlin DSL ORM), H2 for local development and tests and PostgreSQL for production, Thymeleaf templates progressively enhanced with HTMX for the frontend, JUnit 5 with Javalin TestTools for testing, and Javalin's built-in OpenAPI plugin for API documentation.

---

## Consequences

**Easier:**

- Onboarding: the stack has minimal concepts; a new team member can read the routing, handler, and persistence code without understanding a large framework's conventions.
- Controller testing: Javalin TestTools spins up a real in-process server with no HTTP mocking, so integration tests are straightforward to write and reliable.
- Frontend iteration: Thymeleaf + HTMX delivers interactivity via server-side rendering; there is no JS build step, no bundler to configure, and no separate frontend deployment.
- Local development: H2 eliminates the need for a running database to work on or test the system locally.
- API documentation: OpenAPI and Swagger UI are available out of the box via the Javalin plugin; no additional tooling is needed.

**Harder:**

- H2/PostgreSQL parity: H2 does not support all PostgreSQL features (e.g. some JSON operators, certain index types). Queries must be written to the common subset, and any PostgreSQL-specific features must be tested against a real Postgres instance in CI.
- HTMX unfamiliarity: developers accustomed to SPA frameworks will need to shift their mental model to server-driven partial-page updates.
- Exposed maturity: Exposed is less widely adopted than Hibernate or jOOQ. Complex queries may require dropping to raw SQL via Exposed's `exec` API.

**Trade-offs accepted:**

- H2 is not production-equivalent. We accept this for the speed benefit in local development and tests, and we mitigate it by running CI integration tests against PostgreSQL.
- Javalin has a smaller ecosystem than Spring Boot. We accept this because we are deliberately avoiding framework power in favour of simplicity and fast flow.
- HTMX is a progressive enhancement, not a full client-side framework. Features requiring rich client-side state management may need a different approach in future; we accept that risk for now given the booking system's interaction model is primarily form-based.
