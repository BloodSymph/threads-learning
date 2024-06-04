package org.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public final class BoundedBuffer<T> {

    private final T[] elements;

    private final Lock lock;

    private final Condition notFull;

    private final Condition notEmpty;

    private int size;

    @SuppressWarnings("unchecked")
    public BoundedBuffer(final int capacity) {
       this.elements = (T[]) new Object[capacity];
       this.lock = new ReentrantLock();
       this.notFull = this.lock.newCondition();
       this.notEmpty = this.lock.newCondition();
    }

    public boolean isFull() {
        lock.lock();
        try {
            return this.size == this.elements.length;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return this.size == 0;
        } finally {
            lock.unlock();
        }
    }

    public void put(final T element) {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            this.elements[size] = element;
            this.size++;
            System.out.printf("%s was put in buffer. Result buffer: %s%n", element, this);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.lock();;
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            final T result = this.elements[this.size - 1];
            this.elements[size - 1] = null;
            this.size--;
            System.out.printf("%s was taken from buffer. Result buffer: %s%n", result, this);
            notFull.signalAll();
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        lock.lock();
        try {
            return "{" + Arrays.stream(elements, 0, size)
                    .map(Objects::toString)
                    .collect(Collectors.joining(",")) + "}";
        } finally {
            lock.unlock();
        }
    }

}
