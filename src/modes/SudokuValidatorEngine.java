package modes;
import validation.*;
import java.util.ArrayList;
import java.util.List;
import app.*;
public class SudokuValidatorEngine {

    private final int[][] board;

    public SudokuValidatorEngine(int[][] board) {
        this.board = board;
    }

    public List<String> validate(int mode) {
        return switch (mode) {
            case 0 -> validateSequential();
            case 3 -> validate3Threads();
            case 27 -> validate27Threads();
            default -> throw new IllegalArgumentException("Invalid mode: " + mode);
        };
    }

    // ------------------- MODE 0 ------------------------
    private List<String> validateSequential() {
        List<String> allMessages = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            allMessages.addAll(ValidatorFactory.create("row", board, i).validate().getMessages());
            allMessages.addAll(ValidatorFactory.create("col", board, i).validate().getMessages());
            allMessages.addAll(ValidatorFactory.create("box", board, i).validate().getMessages());
        }

        return allMessages;
    }

    // ------------------- MODE 3 ------------------------
    private List<String> validate3Threads() {
        List<String> allMessages = new ArrayList<>();

        Thread rowThread = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                synchronized (allMessages) {
                    allMessages.addAll(ValidatorFactory.create("row", board, i).validate().getMessages());
                }
            }
        });

        Thread colThread = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                synchronized (allMessages) {
                    allMessages.addAll(ValidatorFactory.create("col", board, i).validate().getMessages());
                }
            }
        });

        Thread boxThread = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                synchronized (allMessages) {
                    allMessages.addAll(ValidatorFactory.create("box", board, i).validate().getMessages());
                }
            }
        });

        rowThread.start();
        colThread.start();
        boxThread.start();

        try {
            rowThread.join();
            colThread.join();
            boxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allMessages;
    }

    // ------------------- MODE 27 ------------------------
    private List<String> validate27Threads() {
        List<String> allMessages = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            int idx = i;

            // Row thread
            threads.add(new Thread(() -> {
                var msgs = ValidatorFactory.create("row", board, idx).validate().getMessages();
                synchronized (allMessages) {
                    allMessages.addAll(msgs);
                }
            }));

            // Column thread
            threads.add(new Thread(() -> {
                var msgs = ValidatorFactory.create("col", board, idx).validate().getMessages();
                synchronized (allMessages) {
                    allMessages.addAll(msgs);
                }
            }));

            // Box thread
            threads.add(new Thread(() -> {
                var msgs = ValidatorFactory.create("box", board, idx).validate().getMessages();
                synchronized (allMessages) {
                    allMessages.addAll(msgs);
                }
            }));
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads)
            try { t.join(); } catch (InterruptedException ignored) {}

        return allMessages;
    }
}
