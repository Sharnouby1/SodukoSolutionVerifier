package validation;

import java.util.ArrayList;
import java.util.List;

public class ColumnValidator extends Validator {
    private int colIndex;

    public ColumnValidator(int[][] board, int colIndex) {
        super(board);
        this.colIndex = colIndex;
    }

    public ValidationResult validate() {
        int[] repeated = new int[10];
        List<Integer>[] locations = new ArrayList[10];

        for (int i = 1; i < 10; i++) {
            locations[i] = new ArrayList<>();
        }

        for (int row = 0; row < 9; row++) {
            int value = board[row][colIndex];
            repeated[value]++;
            locations[value].add(row + 1);
        }

        List<String> messages = new ArrayList<>();
        for (int num = 1; num <= 9; num++) {
            if (repeated[num] > 1) {
                messages.add("COL " + (colIndex + 1) + ", #" + num + ", " + locations[num].toString());
            }
        }

        return new ValidationResult(messages);
    }
}
