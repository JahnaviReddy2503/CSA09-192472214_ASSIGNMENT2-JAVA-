# Q7 notes
## A. Diagnose
Q1's repeated concatenation creates intermediate copies; Q2 front operations shift; Q3 violates hashCode/equals; Q4 pays sorting cost when order is requested; Q5 can overflow with subtraction; Q6 can starve severe patients.

## B. Measurable objective
For the same three sizes and ten measured runs, reduce elapsed time for ticket construction while retaining the exact checksum-equivalent text.

## C. Revised design
Pseudocode: `builder = new StringBuilder(); for each field: builder.append(field); return builder.toString()`. It exploits amortized capacity growth and avoids repeated immutable copies. Extra memory is builder capacity plus final text; mutability and unsafe sharing are trade-offs.

## D. Prediction (before implementing)
The redesign should be faster, especially at larger sizes, with a larger improvement than at 1,000 fields.

## E/F. Validation and defence
`Main.java` contains both methods and records identical-condition runs in `results.csv`; `output.txt` has the raw observations. If measurements support the prediction, use the builder for local construction. Original concatenation remains acceptable for tiny, infrequent expressions and where immutability is clearer.
