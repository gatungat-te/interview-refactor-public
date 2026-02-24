# Refactor Interview Exercise

## Context
This Spring Boot sample is intentionally small but designed to feel as part of a larger codebase that should be modularized into clearer, narrower responsibilities.

## Candidate Task
Refactor this project to improve modularity and testability while preserving behavior.

### Constraints
- Keep the refactor small and readable. This is not about adding features.
- Preserve input/output behavior of billing and usage flows.
- You may add tests if helpful, but they are not required.

### Suggested Discussion Topics
- Where should module boundaries live if this were split into subprojects
- Which dependencies would you invert or abstract?
- How would you validate that the refactor did not change billing behavior?
