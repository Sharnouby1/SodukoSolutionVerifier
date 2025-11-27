package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public SudokuBoard readBoard(String filePath) throws IOException {
        int[][] grid = new int[9][9];
        int rowCount = 0;

        // Try-with-resources automatically closes the file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read each line until end of file, or we have 9 rows
            while ((line = br.readLine()) != null && rowCount < 9) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Split the line by commas
                String[] values = line.split(",");

                // Check if we have exactly 9 values
                if (values.length != 9) {
                    throw new IllegalArgumentException(
                            "Row " + (rowCount + 1) + " must have exactly 9 values, but has " + values.length
                    );
                }

                // Convert each value to integer and store in grid
                for (int col = 0; col < 9; col++) {
                    try {
                        grid[rowCount][col] = Integer.parseInt(values[col].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "Invalid number at row " + (rowCount + 1) + ", column " + (col + 1) +
                                        ": '" + values[col] + "' is not a valid integer"
                        );
                    }
                }
                rowCount++;
            }

            // Check if we got exactly 9 rows
            if (rowCount != 9) {
                throw new IllegalArgumentException(
                        "CSV file must have exactly 9 rows with data, but found " + rowCount
                );
            }

        } catch (IOException e) {
            throw new IOException("Error reading file: " + filePath + " - " + e.getMessage(), e);
        }

        return new SudokuBoard(grid);
    }
}