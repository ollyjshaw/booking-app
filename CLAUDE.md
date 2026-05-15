# CLAUDE.md

## Project
<!-- What is this project? One or two sentences. -->

## CLI Routing

When invoked from the terminal:

1. If it is a commit request: trigger the committer agent at effort:low. Confirm the routing decision in one line. Stop.
2. Otherwise: handle it yourself.

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
