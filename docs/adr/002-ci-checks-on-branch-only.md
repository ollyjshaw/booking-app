# ADR 002: Run all CI checks on pull request branches only

Date: 2026-05-15
Status: Accepted
Deciders: architect-system, olly.shaw@equalexperts.com

---

## Context

The Continuous Integration (CI) workflow runs on two triggers: `pull_request` (when a branch is opened or updated) and `push` to `main` (when a branch is merged). Every change is therefore tested twice: once on the branch and once after merge to main.

The duplicate run on main is redundant. Branch protection requires all CI checks to pass before a pull request can be merged. A commit can only reach main if the branch checks already passed. Re-running the same checks on the merged commit wastes GitHub Actions minutes and adds noise to the CI history without providing additional assurance.

---

## Options considered

| Option | Summary | Why rejected |
|--------|---------|--------------|
| Run checks on both `pull_request` and `push` to main | Current state: checks run twice per change | Redundant; branch protection already guarantees checks passed before merge |
| Run checks on `push` to main only | No checks until after merge | Breaks the feedback loop; developers do not learn of failures until after the code lands on main |
| **Run checks on `pull_request` only** | Checks run once, on the branch, before merge | **Chosen** — see Decision below |

---

## Decision

All CI checks run only on pull requests targeting main. The `push: branches: [main]` trigger is removed from the CI workflow. Branch protection must require the `all-checks` status check to pass before any pull request can be merged, ensuring every commit on main was already verified by CI on its branch.

---

## Consequences

**Easier:**

- CI runs once per change instead of twice, halving GitHub Actions usage for every merged pull request.
- The CI history on main is clean: it contains only branch-triggered runs, not post-merge duplicates.
- The feedback loop is unchanged: developers see results while the branch is still open, before merge.

**Harder:**

- If branch protection is ever misconfigured or bypassed (for example, a direct push to main by a repository administrator), CI checks will not run on those commits. We accept this risk because direct pushes to main are prohibited by policy and enforced by branch protection rules.

**Trade-offs accepted:**

- We accept that any commit pushed directly to main (bypassing the pull request flow) will not be verified by CI. Preventing direct pushes to main is a branch protection responsibility, not a CI responsibility.
