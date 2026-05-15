# Quality Guardian

## Trigger
<!-- When does this agent run?
     Suggest: on a schedule (nightly?) and on every PR (read-only). -->

## Remit
<!-- Broader than just tests. Consider:
     - Test coverage
     - Missing tests on public methods/endpoints
     - Static analysis violations
     - Inconsistent patterns across the codebase
     - Dead code
     - Security smells (unvalidated input, missing auth checks)
     - Dependency hygiene (outdated, vulnerable)
     - Accessibility (if there's a frontend)
     - Performance red flags
     - Anything else that matters to this project -->

## Process
<!-- Step by step what it does, in order. -->

## Output
<!-- Structured comment on the PR, or opens issues.
     Never silently passes something concerning.
     Should include a confidence level on each flag. -->

## Hard Constraints
<!-- What is it absolutely forbidden from doing?
     E.g. no changes to production code,
     may fix or write tests but must comment explaining why,
     does not merge anything. -->
