# Q5 notes
## Prediction (before running)
The comparator should produce Eagles, Bisons, Panthers, Cobras and leave the source list unchanged. Chaining gives a transitive total ordering: points descending, goal difference descending, then name ascending. Bisons and Panthers tie numerically, so names decide.

## Design / observed results
`Leaderboard.ranking()` sorts a new copy. The program also compiles bounded generics and a `List<? extends T>` method, and demonstrates subtraction overflow and a missing tie-breaker.

## Complexity / alternatives
Sorting is O(n log n) time and O(n) copy space (plus sort workspace). Comparable puts one natural order on Team; Comparator supports multiple policies. Three stable sorts are less direct and easier to misuse.

## Prediction vs observation / decision
The printed ranking and original list verify non-mutation. Use the comparator chain; never subtract potentially extreme integers.
