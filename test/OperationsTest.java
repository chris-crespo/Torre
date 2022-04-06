package app.tests;

import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;

import app.Operations;
import models.*;

public class OperationsTest {
    @Test
    public void basicTest() {
        var ops = new Operations();
        ops.add(new Landing("A", LocalDateTime.now(), "A", SpecialCause.None));
        ops.add(new TakeOff("B", LocalDateTime.now(), "B"));

        var next = ops.next();
        assertTrue(next.isPresent());
        assertTrue(next.get() instanceof Landing);

        next = ops.next();
        assertTrue(next.isPresent());
        assertTrue(next.get() instanceof TakeOff);
    }

    @Test
    public void intervealedTest() {
        var ops = new Operations();
        ops.add(new Landing("A", LocalDateTime.now(), "A", SpecialCause.None));
        ops.add(new Landing("B", LocalDateTime.now(), "B", SpecialCause.None));
        ops.add(new TakeOff("B", LocalDateTime.now(), "B"));

        var next = ops.next();
        assertTrue(next.isPresent());
        assertTrue(next.get() instanceof Landing);

        next = ops.next();
        assertTrue(next.isPresent());
        assertTrue(next.get() instanceof TakeOff);

        next = ops.next();
        assertTrue(next.isPresent());
        assertTrue(next.get() instanceof Landing);
    }
}
