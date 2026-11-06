# Q6 notes
## Prediction (before running)
PriorityQueue insertion and removal should be O(log n), so the combined triage should grow near n log n. A plain queue ignores severity. Arrival counters make equal-severity order FIFO.

## Design / observed results
The eight-patient case, direct queue print, treatment drain, ten runs at three sizes, starvation example, aging rule, and 5,000-patient day simulation are printed and captured.

## Analysis / alternatives
Heap space is O(n). A sorted LinkedList has O(n) insertion; five FIFO queues offer O(1) operations when severity levels are exactly five but complicate aging and dynamic policies.

## Prediction vs observation / decision
Compare generated timings to the predicted growth. Deploy a priority queue with periodic aging/reprioritization: it preserves urgency while preventing starvation, at the cost of more policy work.
