package validation;

import java.util.ArrayList;
import java.util.List;

public class BoxValidator extends Validator {

    private int boxIndex;

    public BoxValidator(int[][] board,int boxIndex) {
        super(board);
        this.boxIndex = boxIndex;
    }

    @Override
    public ValidationResult validate() {
        int[] repeated = new int [10];
        List<Integer>[] locations = new ArrayList[10];

        for (int i = 1; i < 10; i++) {
            locations[i] = new ArrayList<>();
        }

        int startRow = (boxIndex / 3) * 3;
        int startCol = (boxIndex % 3) * 3;
        int position = 1;

       for(int r= startRow; r < startRow + 3; r++){
           for(int c= startCol; c < startCol + 3; c++){
               int value = board[r][c];
               repeated[value]++;
               locations[value].add(position);
               position++;
           }
       }

        List<String> messages = new ArrayList<>();
        for (int num =1; num <= 9; num++) {
            if(repeated[num] > 1){
                messages.add("BOX " + (boxIndex + 1) + ", #" + num + ", " + locations[num].toString());
            }
        }

        return new ValidationResult(messages);
    }
}
