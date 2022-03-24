package adt.tests;

import org.junit.*;
import static org.junit.Assert.*;

import adt.Queue;

public class QueueTest {
    @Test
    public void basicTest() {
        var q = new Queue<Integer>();
        q.add(1);
        q.add(2);

        assertEquals((int)q.remove().get(), 1);
        assertEquals((int)q.remove().get(), 2);
        assertFalse(q.remove().isPresent());
    }
}
