package org.example;

import java.util.stream.IntStream;

public class SummingNumbers implements Runnable{

    private static final int INITIAL_RESULT = 0;

    private int fromNumber;

    private int toNumber;

    private int result;

    public SummingNumbers(int fromNumber, int toNumber) {
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.result = INITIAL_RESULT;
    }

    public int getResult() {
        return this.result;
    }

    @Override
    public void run() {

        IntStream.rangeClosed(
                this.fromNumber, this.toNumber
        ).forEach(
                i -> this.result += i
        );

        System.out.println(this.result);

    }

}
