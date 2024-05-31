# Multi‐thread Matrix Multiplication 

- Dimensions of the two matrices are program argument inputs
- Number of threads is also main argument of the program
- The program, first checks the arguments and matrix dimensions
- Creates the matrices with random numbers
- Then creates the threads and equally distributes the matrices among
them
- Main program prints the result after calculation of all threads
  - Bonus1: Read the matrices from a text file
  - Bonus2: Record the time for each thread and process time
- Comments: Write it in modular way, comment it well, write a short
report

## Run

### Open project in terminal

```sh
cd src/main/java
```

### Compile and run

```sh
javac org/example/Main.java org/example/MatrixMultiplier.java

# <rows> <cols> <threads> [<file>]

# Run wihout file. Random numbers
java org.example.Main 4 4 4

# Run wih file
java org.example.Main 4 4 4 matrices.txt
```