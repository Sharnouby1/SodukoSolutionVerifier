package app;
import validation.*;

public class ValidatorFactory {

    public static Validator create(String type, int[][] board, int index) {
        switch(type.toLowerCase()) {
            case "row":
                return new RowValidator(board, index);
            case "col":
                return new ColumnValidator(board, index);
            case "box":
                return new BoxValidator(board, index);
            default:
                throw new IllegalArgumentException("Unknown validator type: " + type);
        }
    }
}
