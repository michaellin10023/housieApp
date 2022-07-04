package michael;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Ticket {

    private int id;
    private int[][] grid;
    private int numbersLeft;
    private int numbersMarked = 0;
    private int numbersLeftTopRow;
    private List<Status> status = new LinkedList<Status>(Arrays.asList(Status.NOTHING));

    public void setId(int id) {
        this.id = id;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setNumbersLeft(int numbersLeft) {
        this.numbersLeft = numbersLeft;
    }

    public void setNumbersMarked(int numbersMarked) {
        this.numbersMarked = numbersMarked;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public void setNumbersLeftTopRow(int numbersLeftTopRow) {
        this.numbersLeftTopRow = numbersLeftTopRow;
    }

    public int getId() {
        return id;
    }

    public List<Status> getStatus() {
        return status;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getNumbersLeft() {
        return numbersLeft;
    }

    public int getNumbersMarked() {
        return numbersMarked;
    }

    public int getNumbersLeftTopRow() {
        return numbersLeftTopRow;
    }

}
