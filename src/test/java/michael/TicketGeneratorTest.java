package michael;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TicketGeneratorTest {

    @Test
    public void testGenerate() {

        int[][] ticket = new TicketGenerator(buildInitializer()).generate();
        testNumberCount(ticket);
        testColumnContents(ticket);
    }

    private void testNumberCount(int[][] ticket) {
        int count = 0;
        for(int i=0;i<3;i++) {
            for(int j=0;j<10;j++) {
                if(ticket[i][j] != 0) count++;
            }
        }
        assertEquals(9, count);
    }

    private void testColumnContents(int[][] ticket) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<10;j++) {
                if(ticket[i][j] != 0) {
                    assertTrue(ticket[i][j] > 0 && ticket[i][j] <= 10);
                }
            }
        }
    }

    private Initializer buildInitializer() {
        return new Initializer(10, 1, 3, 3, 10);
    }
}