package michael;

import michael.utils.NumberUtils;

import java.util.List;

public class TicketGenerator {

    private final int row;
    private final int col;
    private final int numPerRow;
    private final int range;

    public TicketGenerator(Initializer initializer) {
        this.row = initializer.getRow();
        this.col = initializer.getCol();
        this.numPerRow = initializer.getNumbersPerRow();
        this.range = initializer.getRange();
    }

    /**
     * This function generates the ticket with random values.
     *
     * @return 2D integer array.
     */
    public int[][] generate() {
        int[][] occupiedPositions = generateIndicesForAllotmentOfNumbers();
        List<Integer> possibleNumbers = NumberUtils.rangedMultiRandNumbGeneratorOneIndexed(range + 1, numPerRow * row);
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (occupiedPositions[i][j] == 1) {
                    occupiedPositions[i][j] = possibleNumbers.get(index++);
                }
            }
        }
        return occupiedPositions;
    }

    /**
     * This function calculates random positions row by row.
     *
     * @return 2D integer array where 1 represent the position is occupied.
     */
    private int[][] generateIndicesForAllotmentOfNumbers() {
        int[][] ticketPositions = new int[row][col];
        for (int i = 0; i < row; i++) {
            List<Integer> rowPositions = NumberUtils.rangedMultiRandNumbGeneratorZeroIndexed(col, numPerRow);
            for (int pos : rowPositions) {
                ticketPositions[i][pos] = 1;
            }
        }
        return ticketPositions;
    }
}
