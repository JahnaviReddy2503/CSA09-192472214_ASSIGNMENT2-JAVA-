# Q1 notes
## Prediction (before running)
Repeated `String` `+` should copy accumulated characters and approach O(n^2); `StringBuilder` and `StringBuffer` should be O(n). I expect synchronization to make `StringBuffer` slower. The short case may hide the difference.

## Design and observed results
`Main.java` runs warm-ups and 10 timed runs for three workloads (the bulk case uses 10,000 generated fields to keep the required repeated-concatenation experiment runnable on this machine), writes the raw rows to `output.txt`, and the same CSV-shaped data is extracted to `results.csv`. It also exercises `join`, `StringJoiner`, `format`, and a shared `StringBuffer` log.

## Prediction vs observation
The measured averages in `output.txt` and `results.csv` are the evidence; this section is completed from those generated values.

## Complexity
Repeated plus is O(n^2) character copying in a loop; builders are amortized O(n). Space is O(n), with many intermediate strings for plus.

## Alternative concept comparison
`String.join` and `StringJoiner` are clear for delimiter-based fields; `String.format` is expressive but formatting-heavy. A shared mutable builder needs external locking, while `StringBuffer` supplies synchronized methods.

## Decision/recommendation
Use `StringBuilder` for each ticket and `StringBuffer` (or a better concurrent logging design) for a shared booking log. Choose from actual benchmark rows rather than assuming a constant factor.
