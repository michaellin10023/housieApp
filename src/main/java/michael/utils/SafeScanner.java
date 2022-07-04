package michael.utils;

import michael.Caller;

import java.util.Scanner;

public class SafeScanner {

    private final Scanner sc;

    public SafeScanner() {
        sc = new Scanner(System.in);
    }

    public int nextInputSafe() {

        int response = 0;
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("Q")) {
            System.exit(0);
        } else {
            try {
                response = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                System.out.println("You did not enter an integer");
                return nextInputSafe();
            }
        }
        return response;
    }

    public void nextOperationSafe(Caller caller) {

        String input = sc.nextLine();
        if (input.equalsIgnoreCase("N")) {
            caller.callNumber();
        } else if (input.equalsIgnoreCase("Q")) {
            System.exit(0);
        }
    }
}
