package modes;

import validation.ValidationResult;

import java.util.List;

public class ModeValidator {

    private final int[][] board;

    public ModeValidator(int[][] board) {
        this.board = board;
    }

    public List<String> runMode(int mode) {
        // Use the engine that you already implemented
        SudokuValidatorEngine engine = new SudokuValidatorEngine(board);

        return engine.validate(mode);
    }
}
