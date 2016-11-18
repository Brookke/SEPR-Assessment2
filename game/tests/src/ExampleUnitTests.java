import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExampleUnitTests {

    @Test
    public void passingTest() {
        assertEquals(1, 1);
    }

    @Test
    public void failingTest() {
        assertEquals(1, 2);
    }
}