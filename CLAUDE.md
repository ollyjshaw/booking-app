# CLAUDE.md

## Project
<!-- What is this project? One or two sentences. -->

## CLI Routing

When invoked from the terminal:

1. If it is a git request (commit, create branch, push, raise PR, sync with main, ship, or returning to main — including informal phrases like "back to main", "go to main", "switch to main", "checkout main"): trigger the git-wrangler agent at effort:low. Confirm the routing decision in one line, naming the command (Commit / Create / Push / PR / Ship / Sync). Stop.
2. If it is a documentation or writing request (READMEs, guides, prose): trigger the technical-author agent at effort:medium. Confirm the routing decision in one line. Stop.
3. Otherwise: handle it yourself.

## Stack
<!-- Language, framework, ORM, DB, formatting tools, static analysis. -->

## Non-negotiables
<!-- Rules every agent must follow without exception.
     Example: all times in UTC, every endpoint has an integration test,
     no PR merges with failing tests, do not touch unrelated code. -->

## Agents
<!-- Brief pointer to docs/agents/ — do not duplicate instructions here. -->
See docs/agents/ for individual agent instructions and remits.

## Ubiquitous Language
<!-- Pointer to the domain glossary. -->
See docs/domain/ubiquitous-language.md. All code, issues, and PRs
must use the terms defined there exactly.
