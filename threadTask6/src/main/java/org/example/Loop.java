package org.example;

public class Loop implements Runnable{

    private final int fromNumber = 0;

    private final int toNumber = 100;

    @Override
    public void run() {
        for (int i = fromNumber; i <= toNumber ; i++) {
            if (i % 10 == 0) {
                System.out.println(i);
            }
        }
    }

}
