# Todos

## Enhancements
- Add other optional simplification options: x+x -> 2*x, x+0 -> x, x*0 -> 0, etc.

## Technical debt
- `precedes()` still uses `instanceof`, rewrite it so it doesn't (?)
- refactor package structure to separate package-private Expression methods (e.g. `reduced()`, `addTo()`) from public ones e.g. `toString()`