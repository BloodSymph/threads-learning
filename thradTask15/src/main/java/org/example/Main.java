package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final EvenNumberGenerator evenNumberGenerator = new EvenNumberGenerator();

        final Runnable generatingTask = () -> IntStream.range(0, 100)
                .forEach(i ->
                        System.out.println(
                                evenNumberGenerator.generate()
                        )
                );

        final Thread firstThread = new Thread(generatingTask);
        firstThread.start();

        final Thread secondThread = new Thread(generatingTask);
        secondThread.start();

        final Thread thirdThread = new Thread(generatingTask);
        thirdThread.start();

    }

    private static final class EvenNumberGenerator {

        private final Lock lock;

        private int previousGenerated;

        public EvenNumberGenerator() {
            this.lock = new ReentrantLock();
            this.previousGenerated = -2;
        }

        public int generate() {
            return lock.tryLock() ?
                    onSuccessesAcquireLock() :
                    onFailAcquireLock();
        }

        private int onSuccessesAcquireLock() {
            try {
                return previousGenerated += 2;
            } finally {
                lock.unlock();
            }
        }

        private int onFailAcquireLock() {
            System.out.printf(
                    "Thread '%s' did not acquire lock.\n",
                    Thread.currentThread().getName()
            );
            throw new RuntimeException();
        }

    }
}