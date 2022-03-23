package adt;

import java.util.Optional;

class Node<T> {
    T value;
    Node<T> next;

    Node(T value, Node<T> next) {
        this.value = value;
        this.next  = next;
    }
}

public class Queue<T> {
    Node<T> front;
    Node<T> rear;

    public Queue() {
        this.front = null;
        this.rear  = null;
    }

    public void add(T value) {
        var node = new Node<T>(value, null);
        if (front == null)
            front = node;
        else
            rear.next = node;

        rear  = node;
    }

    public Optional<T> remove() {
        if (front == null)
            return Optional.empty();

        var value = front.value;
        front = front.next;
        return Optional.of(value);
    }

    public Optional<T> peekLast() {
        if (front == null)
            return Optional.empty();

        return Optional.of(rear.value);
    }
}
