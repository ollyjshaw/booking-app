# ADR 003: Use Claude GitHub Action for spec and implementation workflow

Date: 2026-05-15
Status: Accepted
Deciders: architect-system, olly.shaw@equalexperts.com

---

## Context

We want to move from a feature idea (GitHub issue) to a written spec and then to a working implementation without requiring a development machine at any point. The workflow must be operable entirely from a browser or mobile device.

We considered two broad approaches: a scheduled polling agent that watches GitHub for labelled issues, and an event-driven approach triggered by comments or labels on issues.

---

## Options considered

| Option | Summary | Why rejected |
|--------|---------|--------------|
| Scheduled remote agent (polling) | A Claude agent runs every N minutes, picks up labelled issues, and writes specs/code | Polling lag, wasteful runs when there is nothing to do, requires external scheduling infrastructure |
| Webhook + custom endpoint | GitHub webhook fires on issue events; a custom service triggers a Claude agent | Requires hosting a webhook receiver; more infrastructure to maintain |
| **Claude GitHub Action (`@claude` trigger)** | The official Anthropic GitHub Action runs Claude in GitHub's CI infrastructure when `@claude` is mentioned in a comment | **Chosen** — see Decision below |

---

## Decision

Use `anthropics/claude-code-action@v1` in a GitHub Actions workflow (`.github/workflows/claude.yml`) triggered by issue and PR comments. Two human-gated phases:

1. Comment `@claude spec this` on an issue → Claude opens a PR with `docs/specs/<issue-number>-<slug>.md`
2. After the spec PR is reviewed and merged, comment `@claude implement this` on the issue → Claude opens an implementation PR

Each phase produces a reviewable PR. Neither fires automatically — the `@claude` mention is the explicit human trigger.

---

## Consequences

**Easier:**

- No development machine needed: the entire workflow runs from a GitHub issue page.
- Two natural review gates (spec PR, implementation PR) catch misunderstandings before code is written.
- GitHub Actions provides the infrastructure — no servers, webhooks, or scheduled jobs to maintain.
- The trigger is explicit and intentional; nothing fires by accident.

**Harder:**

- Requires `ANTHROPIC_API_KEY` to be stored as a GitHub Actions secret and rotated like any other credential.
- GitHub Actions minutes are consumed per run; a noisy repo with many `@claude` mentions will burn quota faster.
- The action runs in a fresh environment each time — no persistent state between runs beyond what is committed to the repo.

**Trade-offs accepted:**

- We accept the manual `@claude` trigger rather than full automation. The human comment is a lightweight but meaningful gate — it means someone has decided the issue is ready to spec or implement.
- We accept that the workflow is limited to what the Claude GitHub Action supports. More complex orchestration (e.g. domain architect review before implementation) can be layered on later.
