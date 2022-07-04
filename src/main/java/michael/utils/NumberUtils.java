package michael.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class NumberUtils {

    private static final Random rand = new Random();

    /**
     * This function returns a random number in range 0 to n-1
     *
     * @param n
     * @return random number
     */
    public static int randomNumber(int n) {
        return rand.nextInt(n);
    }

    /**
     * This function returns a list of random numbers in a given range. (0 indexed)
     *
     * @param size
     * @param count
     * @return list of random numbers
     */
    public static List<Integer> rangedMultiRandNumbGeneratorZeroIndexed(int size, int count) {
        List<Integer> pool = new LinkedList<Integer>();
        for (int i = 0; i < size; i++) {
            pool.add(i);
        }
        List<Integer> pickedNumbers = new LinkedList<Integer>();
        for (int i = 0; i < count; i++) {
            int randomIndex = randomNumber(size - i);
            // pick the random number from the pool
            int randomNumber = pool.get(randomIndex);
            // O(n) time complexity
            pool.remove(randomIndex);
            pickedNumbers.add(randomNumber);
        }
        return pickedNumbers;
    }

    /**
     * This function returns a list of random numbers in a given range. (1 indexed)
     *
     * @param size
     * @param count
     * @return list of random numbers
     */
    public static LinkedList<Integer> rangedMultiRandNumbGeneratorOneIndexed(int size, int count) {
        List<Integer> pool = new LinkedList<Integer>();
        for (int i = 1; i < size; i++) {
            pool.add(i);
        }
        List<Integer> pickedNumbers = new LinkedList<Integer>();
        for (int i = 0; i < count; i++) {
            int randomIndex = randomNumber(size - i - 1);
            // pick the random number from the pool
            int randomNumber = pool.get(randomIndex);
            pool.remove(randomIndex);
            pickedNumbers.add(randomNumber);
        }
        return new LinkedList<Integer>(pickedNumbers);
    }
}
