# CLAUDE.md

## Project
<!-- What is this project? One or two sentences. -->

## CLI Routing

When invoked from the terminal:

1. If it is a git request (commit, create branch, push, raise PR, sync with main, ship, or returning to main — including informal phrases like "back to main", "go to main", "switch to main", "checkout main"): handle it yourself inline following the instructions in docs/agents/git-wrangler.md. Confirm the routing decision in one line, naming the command (Commit / Create / Push / PR / Ship / Sync). Stop.
   <!-- Inline rather than subagent: spawning an agent adds ~8–10k tokens of overhead for what are just a handful of shell commands. Not worth it. -->
2. If it is an architectural request (design discussion, ADR, system trade-offs, structure, operability, rollback, or anything that sounds like "how should we build/structure/design X"): handle it yourself inline following the instructions in docs/agents/architect-system.md, CLI route. Confirm the routing decision in one line. Stop.
   <!-- Inline rather than subagent: the CLI route is conversational — a subagent would lose the thread immediately. -->
3. If it is a documentation or writing request (READMEs, guides, prose): trigger the technical-author agent at effort:medium. Confirm the routing decision in one line. Stop.
4. Otherwise: handle it yourself.

## Stack
<!-- Language, framework, ORM, DB, formatting tools, static analysis. -->
Web framework: Javalin 7.2.2 (Java, Jetty 12). Full API reference is imported below and is always in context — read it before writing any route, handler, or test.

@docs/frameworks/javalin.md

## Non-negotiables
<!-- Rules every agent must follow without exception.
     Example: all times in UTC, every endpoint has an integration test,
     no PR merges with failing tests, do not touch unrelated code. -->
- **PR bodies never contain a test plan, test checklist, or any testing tasks.** This applies regardless of which agent raises the PR and regardless of what PR template you might otherwise default to.

## Agents
<!-- Brief pointer to docs/agents/ — do not duplicate instructions here. -->
See docs/agents/ for individual agent instructions and remits.

## Ubiquitous Language
<!-- Pointer to the domain glossary. -->
See docs/domain/ubiquitous-language.md. All code, issues, and PRs
must use the terms defined there exactly.
