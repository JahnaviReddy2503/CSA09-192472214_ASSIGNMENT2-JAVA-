# Q3 notes
## Prediction (before running)
T1 should appear correct with distinct registration numbers; T2 should expose duplicate logical students without `hashCode`; T3 demonstrates the mutation hazard (this implementation uses final identity fields, so mutation is prevented). After the fix the required count is 2.

## Design / observed results
`Broken` intentionally omits `hashCode`; `Student` implements the contract from `regNo`, with identity short-circuit and the requested `toString`. All predictions and outputs are printed by the program.

## Explanation and complexity
HashSet chooses a bucket with `hashCode` before calling `equals`, so equal objects in different buckets survive. Average add/contains/remove is O(1), worst case O(n), with O(n) space. TreeSet is O(log n); a HashMap makes the key explicit.

## Prediction vs observation / decision
The before/after and T3 lines in `output.txt` verify the contract fix. Use the fixed Student class; use TreeSet when sorted traversal is the primary requirement.
