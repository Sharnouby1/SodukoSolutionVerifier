package app;
import io.CSVReader;
import io.SudokuBoard;
import modes.*;
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Usage: java -jar app.jar <csv file> <mode>");
                return;
            }

            String filePath = args[0];
            int mode = Integer.parseInt(args[1]);

            CSVReader reader = new CSVReader();
            SudokuBoard board = reader.readBoard(filePath);

            SudokuValidatorEngine engine = new SudokuValidatorEngine(board.getGrid());
            var messages = engine.validate(mode);

            if (messages.isEmpty()) {
                System.out.println("VALID");
            } else {
                System.out.println("INVALID");
                messages.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
