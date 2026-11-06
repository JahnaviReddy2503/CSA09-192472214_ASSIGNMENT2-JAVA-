# Q2 notes
## Prediction (before running)
ArrayList should be O(1) amortized at the end and random access, but O(n) front/middle shifts. LinkedList has O(1) link changes only after locating a node, so indexed middle access is O(n). ArrayDeque should be O(1) at both ends.

## Design / observed results
Three sizes (1,000, 10,000, 100,000), warm-ups, ten runs, and an Up Next deque are executed. `output.txt` and `results.csv` contain the measurements.

## Prediction vs observation
Use the generated averages to compare the expected growth; results are machine-specific and intentionally not invented here.

## Complexity / alternatives
ArrayList stores O(n) contiguous references; LinkedList stores O(n) nodes with link overhead; ArrayDeque uses a circular O(n) array. A manual array has the same shift cost as ArrayList and exposes resizing details.

## Decision
Use ArrayList for indexed/read-heavy playlists, ArrayDeque for Up Next, and stop recommending LinkedList unless operations are truly node-local or a deque API is insufficient.
