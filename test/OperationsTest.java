package app.tests;

import java.util.Date;

import org.junit.*;
import static org.junit.Assert.*;

import app.Operations;
import models.*;

public class OperationsTest {
    @Test
    public void basicTest() {
        var ops = new Operations();
        ops.add(new Landing("A", new Date(), "A", SpecialCause.None));
        ops.add(new TakeOff("B", new Date(), "B"));

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
        ops.add(new Landing("A", new Date(), "A", SpecialCause.None));
        ops.add(new Landing("B", new Date(), "B", SpecialCause.None));
        ops.add(new TakeOff("B", new Date(), "B"));

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
