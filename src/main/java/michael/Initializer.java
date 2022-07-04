package michael;

import michael.Exception.InputInvalidException;
import michael.utils.SafeScanner;

public class Initializer {

    private static final SafeScanner safeScanner = new SafeScanner();
    private final int range;
    private final int numberOfPlayers;
    private final int numbersPerRow;
    private final int row;
    private final int col;

    // Should be private ideally, but for easier testing purpose I have make it protected here.
    protected Initializer(int range, int numberOfPlayers, int numbersPerRow, int row, int col) {
        this.range = range;
        this.numberOfPlayers = numberOfPlayers;
        this.numbersPerRow = numbersPerRow;
        this.row = row;
        this.col = col;
    }

    public static Initializer getInstance() {

        System.out.println("===== Input Sample =====");
        System.out.println("**** Lets Play Housie ****\n");

        System.out.println("Note: Press 'Q' to quit any time");

        System.out.println(">>Enter the number range(1 - n): ");
        int range = safeScanner.nextInputSafe();

        System.out.println(">>Enter Number of players playing the game: ");
        int numberOfPlayers = safeScanner.nextInputSafe();

        System.out.println(">>Enter Ticket Size: Default to 3 x 10 (3 rows and 10 columns): ");
        int row = safeScanner.nextInputSafe();
        int col = safeScanner.nextInputSafe();

        System.out.println(">>Enter numbers per row. Default to 5: ");
        int numbersPerRow = safeScanner.nextInputSafe();

        if (row * numbersPerRow > range) {
            throw new InputInvalidException("Range needs to be greater than row * numbersPerRow!");
        } else if (numbersPerRow > col) {
            throw new InputInvalidException("Numbers per row can not be greater than column!");
        }

        System.out.println("**** Ticket Created Successfully ****");
        System.out.println("========================\n");

        return new Initializer(range, numberOfPlayers, numbersPerRow, row, col);
    }

    public int getRange() {
        return range;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumbersPerRow() {
        return numbersPerRow;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
