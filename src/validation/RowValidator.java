package validation;

import java.util.ArrayList;
import java.util.List;

public class RowValidator extends Validator {

    private int rowIndex;

    public RowValidator(int[][] board, int rowIndex) {
        super(board);
        this.rowIndex = rowIndex;
    }

    public ValidationResult validate() {
        int[] repeated = new int [10];
        List<Integer>[] locations = new ArrayList[10];

        for (int i = 1; i < 10; i++) {
            locations[i] = new ArrayList<>();
        }

        for (int col = 0; col < 9; col++) {
            int value = board[rowIndex][col];
            repeated[value]++;
            locations[value].add(col + 1);
        }

        List<String> messages = new ArrayList<>();
        for (int num =1; num <= 9; num++) {
            if(repeated[num] > 1){
                messages.add("ROW " + (rowIndex + 1) + ", #" + num + ", " + locations[num].toString());
            }
        }

        return new ValidationResult(messages);
    }

}
