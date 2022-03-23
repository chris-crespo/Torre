import org.junit.*;
import static org.junit.Assert.*;

public class StreamTest {
    @Test
    public void infiniteTest() {
        var s = Stream.infinite(() -> 0);
        for (int i = 0; i < 10; i++)
            assertEquals((int)s.next(), 0);
    }

    @Test
    public void interleaveTest() {
        var zeros = Stream.infinite(() -> 0);
        var ones  = Stream.infinite(() -> 1);
        var s     = zeros.interleave(ones);

        for (int i = 0; i < 10; i++) {
            assertEquals((int)s.next(), 0);
            assertEquals((int)s.next(), 1);
        }
    }
}
