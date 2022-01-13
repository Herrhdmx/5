package de.hfu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueTest {

    private Queue queue;

    @Before
    public void before() {
        queue = new Queue(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void queueWithSize0ThrowsIllegalArgumentException() {
        new Queue(0);
    }

    @Test
    public void firstEnqueuedValueBecomesHead() {
        int value = 7;
        queue.enqueue(value);
        queue.enqueue(9);

        assertEquals(value, queue.queue[queue.head]);
    }

    @Test
    public void lastEnqueuedValueBecomesTail() {
        int value = 5;
        queue.enqueue(2);
        queue.enqueue(value);

        assertEquals(value, queue.queue[queue.tail]);
    }

    @Test
    public void whenQueueIsFullLastEnqueuedValueBecomesTail() {
        int value = 10;
        queue.enqueue(9);
        queue.enqueue(2);
        queue.enqueue(14);
        queue.enqueue(value);

        assertEquals(value, queue.queue[queue.tail]);
    }

    @Test
    public void dequeueReturnsCurrentHead() {
        int head = 7;
        queue.enqueue(head);
        queue.enqueue(23);
        queue.enqueue(4);

        assertEquals(head, queue.dequeue());
    }

    @Test
    public void dequeueIncrementsQueueHead() {
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(6);

        int previousHead = queue.head;
        queue.dequeue();
        int actual = queue.head;

        assertEquals(previousHead + 1, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void dequeueThrowsIllegalStateExceptionWhenQueueEmpty() {
        queue.enqueue(3);
        queue.enqueue(6);
        queue.enqueue(9);

        // empty queue
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        // throws
        queue.dequeue();
    }
}