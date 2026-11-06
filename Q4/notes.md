# Q4 notes
## Prediction (before running)
HashMap lookup should be average O(1), TreeMap O(log n), LinkedHashMap O(1). TreeMap naturally gives code order, LinkedHashMap insertion order, and HashMap needs sorting for a code report.

## Design / observed results
Each size has 100,000 lookups and ten measured runs. Code-order and insertion-order report timings plus an in-code order flag are recorded in `output.txt` and `results.csv`.

## Analysis / decision
Define acceptable lookup time as the smallest stable measured average that meets the application latency target; the target must be set by deployment measurement. Choose TreeMap for continuously code-sorted reports, LinkedHashMap for insertion-order reports, and HashMap when order is irrelevant. Remaining report cost comes from traversal or sorting. A sorted array plus binarySearch is compact but insertion shifts are O(n). Hashtable is not preferred because its legacy synchronization and null restrictions do not match these needs.

## Complexity and space
HashMap/LinkedHashMap put/get average O(1), TreeMap O(log n); iteration is O(n), sorting an unordered report O(n log n). All use O(n) space, with tree/node overhead varying.

## Observed results
The first three measured rows were: 1000,HashMap,1,243867700,2274000,240800,true; 1000,HashMap,2,171316600,953900,52500,true; 1000,HashMap,3,155751400,511000,133900,true. Complete averages can be recomputed from the 90 raw rows.
