package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public final class Task implements Runnable {

    private static final String MESSAGE_ACQUIRE = "Thread '%s' is trying acquire lock '%s'\n";

    private static final String MESSAGE_SUCCESS = "Thread '%s' acquire lock '%s'\n";

    private static final String MESSAGE_RELEASE = "Thread '%s' released lock '%s'\n";

    private static final String FIRST_LOCK = "firstLock";

    private static final String SECOND_LOCK = "secondLock";

    private final Lock firstLock;

    private final Lock secondLock;

    public Task(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        final String currentThreadName = Thread.currentThread().getName();
        System.out.printf(MESSAGE_ACQUIRE, currentThreadName, FIRST_LOCK);
        this.firstLock.lock();
        try {
            System.out.printf(MESSAGE_SUCCESS, currentThreadName, FIRST_LOCK);
            TimeUnit.MILLISECONDS.sleep(50);
            while (!this.tryAcquireSecondLock()) {
                TimeUnit.MILLISECONDS.sleep(50);
                this.firstLock.unlock();
                System.out.printf(MESSAGE_RELEASE, currentThreadName, FIRST_LOCK);
                TimeUnit.MILLISECONDS.sleep(50);
                System.out.printf(MESSAGE_ACQUIRE, currentThreadName, FIRST_LOCK);
                this.firstLock.lock();
                System.out.printf(MESSAGE_ACQUIRE, currentThreadName, FIRST_LOCK);
                TimeUnit.MILLISECONDS.sleep(50);
            }
            try {
                System.out.printf(MESSAGE_SUCCESS, currentThreadName, SECOND_LOCK);
            } finally {
                this.secondLock.unlock();
                System.out.printf(MESSAGE_RELEASE, currentThreadName, SECOND_LOCK);
            }
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        } finally {
            this.firstLock.unlock();
            System.out.printf(MESSAGE_RELEASE, currentThreadName, FIRST_LOCK);
        }
    }

    private boolean tryAcquireSecondLock() {
        final String currentThreadName = Thread.currentThread().getName();
        System.out.printf(MESSAGE_ACQUIRE, currentThreadName, SECOND_LOCK);
        return this.secondLock.tryLock();
    }

}
