package validation;

public abstract class Validator {

    protected int[][] board;

    public Validator(int[][] board) {
        this.board = board;
    }

    public abstract ValidationResult validate();
}
