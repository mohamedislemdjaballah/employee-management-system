# Java Practice Exercises (18 files)

Four sets of exercises for a beginner Java course. Every file starts with a
description, the task, and the expected output in its header comment (written
in French).

## 1. Fix the errors (`01-fix-the-errors/`)

These files **intentionally do not compile**. Students fix them one error at a
time, recompiling after each change.

| File | Topic | Errors |
|------|-------|--------|
| `Greeting.java` | variables, strings, `println` | 4 |
| `Calculator.java` | methods, return types, `if` | 4 |
| `LoopPractice.java` | arrays, `for`, `do-while` | 5 |
| `StudentReport.java` | objects, constructors, quotes and semicolons | 4 |
| `Temperature.java` | types, casts, `if/else` | 4 |

```bash
cd 01-fix-the-errors
javac Greeting.java     # fails at first; repeat until it compiles
java Greeting
```

## 2. Translate pseudocode (`02-translate-pseudocode/`)

Each file contains commented pseudocode and a method stub with a `TODO`. The
`main` method prints the actual result next to the expected one, so students get
immediate feedback.

| File | Topic |
|------|-------|
| `SumEvenNumbers.java` | `for` loop, modulo, accumulator |
| `CountVowels.java` | string iteration, `charAt`, `Character.toLowerCase` |
| `FindMaximum.java` | arrays, comparisons, empty array |
| `IsPalindrome.java` | two indexes moving toward each other |
| `IsPrime.java` | `while` loop, early `return` |

```bash
cd 02-translate-pseudocode
javac SumEvenNumbers.java && java SumEvenNumbers
```

## 3. Write the program yourself (`03-write-yourself/`)

Only the problem statement and the expected output are given. Students write
everything inside `main` (or, for `BankAccount`, also design the class).

| File | Topic |
|------|-------|
| `GradeAverage.java` | loops, average, min/max, grading logic |
| `ShoppingCart.java` | parallel arrays, totals, conditional discount |
| `MultiplicationTable.java` | nested loops, `printf` formatting |
| `WordStats.java` | `split`, word count, longest word, character count |
| `BankAccount.java` | class design, fields, constructor, validation |

```bash
cd 03-write-yourself
javac BankAccount.java && java BankAccount
```

## 4. Classes (`04-classes/`)

Students design a small class themselves: fields, constructor, methods, and a
`main` that exercises the object. The skeleton compiles, so they can start
coding immediately.

| File | Topic |
|------|-------|
| `Person.java` | create a class, constructor, full name, update an attribute |
| `Counter.java` | private field, increment/add/reset, read the value |
| `NumberPair.java` | two fields, sum, product, setter |

```bash
cd 04-classes
javac Person.java && java Person
```

## Solutions (`solutions/`)

The solutions mirror the exercise folders and use the same file names:

```
solutions/
├── 01-fix-the-errors/        corrected versions of the 5 broken files
├── 02-translate-pseudocode/  the 5 completed methods
├── 03-write-yourself/        the 5 complete programs
└── 04-classes/               the 3 completed classes
```

Each solution keeps a short header explaining the fixes or the approach.
Compile and run them the same way:

```bash
cd solutions/03-write-yourself
javac BankAccount.java && java BankAccount
```

## Notes for the instructor

- All files compile with a standard JDK (tested with JDK 26) apart from the
  five in set 1, which are supposed to fail.
- Every set compiles independently; no packages or external libraries.
- Suggested timing: 15 minutes per exercise, or one file per student with a
  short peer review at the end.
- Hand out only the four exercise folders; keep `solutions/` for yourself.

