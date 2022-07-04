package michael;

import michael.utils.NumberUtils;

import java.util.LinkedList;
import java.util.List;

public class Caller {

    private static final int FIVE = 5;
    private final int range;
    private final List<Ticket> tickets;
    private final LinkedList<Integer> possibleValues;
    private boolean gameEndFlag = false;
    private boolean fullHouseFlag = false;
    private boolean earlyFiveFlag = false;
    private boolean topLineFlag = false;

    public Caller(Initializer initializer, List<Ticket> tickets) {
        this.range = initializer.getRange();
        this.tickets = tickets;
        this.possibleValues = NumberUtils.rangedMultiRandNumbGeneratorOneIndexed(range + 1, range);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public boolean isGameEnded() {
        return gameEndFlag;
    }

    /**
     * Caller calls a number and mark players' ticket and check the winning combinations.
     *
     * @return
     */
    public int callNumber() {

        int number = possibleValues.get(0);
        possibleValues.removeFirst();
        System.out.println("Next number is: " + number);

        markNumber(number, tickets);
        checkState();
        return number;
    }

    /**
     * Winning combinations includes the following:
     * Full House: The ticket with all the numbers marked first.
     * Early Five: The ticket with first five number marked.
     * Top Line: The ticket with all numbers in top line.
     */
    private void checkState() {

        checkFullHouse(tickets);
        checkTopLine(tickets);
        checkEarlyFive(tickets);
        checkNumbersLeft();
    }

    /**
     * This function marks number from list of tickets.
     *
     * @param number
     * @param tickets
     */
    private void markNumber(int number, List<Ticket> tickets) {

        for (Ticket ticket : tickets) {
            int[][] grid = ticket.getGrid();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == number) {
                        // mark number to 0
                        grid[i][j] = 0;
                        ticket.setNumbersLeft(ticket.getNumbersLeft() - 1);
                        ticket.setNumbersMarked(ticket.getNumbersMarked() + 1);
                        if (i == 0) ticket.setNumbersLeftTopRow(ticket.getNumbersLeftTopRow() - 1);
                    }
                }
            }
        }
    }

    /**
     * This function checks full house combination among all the tickets.
     *
     * @param tickets
     */
    private void checkFullHouse(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {
            if (ticket.getNumbersLeft() == 0 && !fullHouseFlag) {
                fullHouseFlag = true;
                logResult(addStatus(ticket, Status.FULL_HOUSE));
            }
        }
    }

    /**
     * This function checks early five combination among all the tickets.
     *
     * @param tickets
     */
    private void checkEarlyFive(List<Ticket> tickets) {

        if (!earlyFiveFlag) {
            for (Ticket ticket : tickets) {
                if (ticket.getNumbersMarked() == FIVE && !earlyFiveFlag) {
                    earlyFiveFlag = true;
                    logResult(addStatus(ticket, Status.EARLY_FIVE));
                }
            }
        }
    }

    /**
     * This function checks top line combination among all the tickets.
     *
     * @param tickets
     */
    private void checkTopLine(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {
            int[][] grid = ticket.getGrid();
            boolean notDone = false;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[0][j] != 0) {
                    notDone = true;
                }
            }

            if (!notDone && ticket.getNumbersLeftTopRow() == 0 && !topLineFlag) {
                topLineFlag = true;
                logResult(addStatus(ticket, Status.TOP_LINE));
            }
        }
    }

    /**
     * This function adds the claimed combination for given ticket.
     *
     * @param ticket
     * @param status
     * @return Ticket
     */
    private Ticket addStatus(Ticket ticket, Status status) {

        List<Status> statusList = ticket.getStatus();
        statusList.remove(Status.NOTHING);
        if (!statusList.contains(status)) {
            statusList.add(status);
        }
        ticket.setStatus(statusList);
        return ticket;
    }

    /**
     * Sets flag to true if all the combinations have been claimed.
     */
    private void checkNumbersLeft() {
        gameEndFlag = (fullHouseFlag && earlyFiveFlag && topLineFlag);
    }

    /**
     * Helper function to print the result.
     *
     * @param ticket
     */
    private void logResult(Ticket ticket) {
        System.out.println("We have a winner: Player #" + (ticket.getId() + 1) + " has won " + ticket.getStatus() + " winning combination");
    }
}
