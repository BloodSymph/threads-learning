package org.example;

public final class Task extends Thread {

    private static int FROM = 0;

    private static int TO = 100;

    private final StringBuilder stringBuilder;

    public Task(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void run() {
        synchronized (stringBuilder) {
            while (FROM < TO) {
                System.out.println(stringBuilder.append(FROM++));
                FROM++;
            }
        }
    }
}
