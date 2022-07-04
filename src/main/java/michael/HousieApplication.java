package michael;

import michael.Exception.InputInvalidException;
import michael.utils.SafeScanner;

import java.util.ArrayList;
import java.util.List;

public class HousieApplication {

    private static final SafeScanner safeScanner = new SafeScanner();
    private static Caller caller;

    public static void main(String[] args) {

        try {
            HousieApplication housieApplication = new HousieApplication();
            Initializer initializer = Initializer.getInstance();
            TicketGenerator ticketGenerator = new TicketGenerator(initializer);

            List<Ticket> tickets = new ArrayList<Ticket>();
            for (int i = 0; i < initializer.getNumberOfPlayers(); i++) {

                Ticket ticket = new Ticket();
                ticket.setId(i);
                ticket.setGrid(ticketGenerator.generate());
                ticket.setNumbersLeft(initializer.getNumbersPerRow() * initializer.getRow());
                ticket.setNumbersLeftTopRow(initializer.getNumbersPerRow());
                tickets.add(ticket);
            }

            housieApplication.print(tickets);
            caller = new Caller(initializer, tickets);
            housieApplication.startGame();
            housieApplication.summary();
        } catch (InputInvalidException ive) {
            System.out.println(ive.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Helper function to start the game process.
     */
    private void startGame() {

        System.out.println("===== Output Sample =====");
        while (true) {

            System.out.println(">>Press 'N' to generate next number");
            safeScanner.nextOperationSafe(caller);

            if (gameEnded()) {
                System.out.println("\nAll combinations are claimed! Game ended!");
                break;
            }
        }
        System.out.println("========================\n");
    }

    /**
     * Helper function to print the summary.
     */
    private void summary() {

        System.out.println("========================");
        System.out.println("        Summary");
        for (Ticket ticket : caller.getTickets()) {
            System.out.println("Player#" + (ticket.getId() + 1) + " : " + ticket.getStatus());
        }
        System.out.println("========================");
    }

    private boolean gameEnded() {
        return caller.isGameEnded();
    }

    /**
     * Helper function to print the tickets of each player for debugging purpose.
     *
     * @param tickets
     */
    private void print(List<Ticket> tickets) {

        for (Ticket ticket : tickets) {
            System.out.println("Player#" + (ticket.getId() + 1));
            int[][] grid = ticket.getGrid();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
