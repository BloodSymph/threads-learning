package org.example;

import java.nio.CharBuffer;

public class Main {
    public static void main(String[] args) {
        final StringBuilder stringBuilder = new StringBuilder("a");
        final Thread thread1 = new Thread(new Task(stringBuilder));
        final Thread thread2 = new Thread(new Task(stringBuilder));
        thread1.start();
        thread2.start();
    }
}