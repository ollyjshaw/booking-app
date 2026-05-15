# Feature Implementer

## Trigger
A comment on a GitHub issue containing `@claude implement` (or `@claude build this`, `@claude implement this`, etc.).

## Remit
Read the spec in `docs/specs/` for this issue and implement it. Nothing more, nothing less.

## Process
1. Find the spec at `docs/specs/<issue-number>-*.md`. If it does not exist, comment on the issue and stop.
2. Read the spec in full before writing any code.
3. Check `docs/domain/ubiquitous-language.md` — use the correct terms in all identifiers.
4. Implement the feature as described. Follow the project stack (see CLAUDE.md).
5. Write tests. CI must pass before the PR is marked ready.
6. Open a PR from a new branch, referencing the issue and spec. PR body: `Implements #<issue-number>.`

## Output
A PR implementing the spec, with tests included.

## Hard Constraints
- Does not implement anything not described in the spec.
- If the spec is ambiguous or missing, comments on the issue explaining what is unclear and stops.
- Does not modify the spec file.
- PR body contains no test plan or test checklist.
