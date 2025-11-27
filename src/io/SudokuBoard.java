package io;

public class SudokuBoard {
    private final int[][] grid;
    private static final int SIZE = 9;
    private static final int BOX_SIZE = 3;

    // Constructor - creates a new board from a 9x9 grid
    public SudokuBoard(int[][] grid) {
        // Check if the grid is exactly 9x9
        if (grid.length != SIZE) {
            throw new IllegalArgumentException("Board must have exactly 9 rows");
        }
        for (int i = 0; i < SIZE; i++) {
            if (grid[i].length != SIZE) {
                throw new IllegalArgumentException("Each row must have exactly 9 columns");
            }
        }
        this.grid = grid;
    }

    // Get a specific value from the board
    public int getValue(int row, int col) {
        return grid[row][col];
    }

    // Get an entire row (horizontal line)
    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= SIZE) {
            throw new IllegalArgumentException("Row index must be between 0 and 8");
        }
        return grid[rowIndex].clone(); // Return a copy to protect the original data
    }

    // Get an entire column (vertical line)
    public int[] getColumn(int colIndex) {
        if (colIndex < 0 || colIndex >= SIZE) {
            throw new IllegalArgumentException("Column index must be between 0 and 8");
        }

        int[] column = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            column[i] = grid[i][colIndex];
        }
        return column;
    }

    // Get a 3x3 box
    public int[] getBox(int boxIndex) {
        if (boxIndex < 0 || boxIndex >= SIZE) {
            throw new IllegalArgumentException("Box index must be between 0 and 8");
        }

        int[] box = new int[SIZE];

        int startRow = (boxIndex / BOX_SIZE) * BOX_SIZE;
        int startCol = (boxIndex % BOX_SIZE) * BOX_SIZE;

        int index = 0;
        for (int i = startRow; i < startRow + BOX_SIZE; i++) {
            for (int j = startCol; j < startCol + BOX_SIZE; j++) {
                box[index] = grid[i][j];
                index++;
            }
        }
        return box;
    }

    // Get the size of the board (always 9)
    public int getSize() {
        return SIZE;
    }

    // Display the board as a string (for testing)
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sudoku Board:\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(grid[i][j]);
                if (j < SIZE - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}