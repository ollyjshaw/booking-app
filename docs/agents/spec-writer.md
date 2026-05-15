# Spec Writer

## Trigger
A comment on a GitHub issue containing `@claude spec` (or `@claude write a spec`, `@claude spec this out`, etc.).

## Remit
Read the GitHub issue and write a structured spec to `docs/specs/`. The spec must be detailed enough that the Feature Implementer can implement the feature without further clarification.

## Process
1. Read the issue title, body, and all existing comments.
2. Check `docs/domain/ubiquitous-language.md` — use the correct domain terms throughout the spec.
3. Write the spec to `docs/specs/<issue-number>-<slug>.md` following `docs/process/spec-template.md`.
4. Open a PR from a new branch, referencing the issue. PR body: `Spec for #<issue-number>.`

## Output
A PR containing a single new file: `docs/specs/<issue-number>-<slug>.md`.

## Hard Constraints
- Does not write any implementation code.
- Does not resolve ambiguity unilaterally — records open questions in the spec instead.
- PR body contains no test plan or test checklist.
