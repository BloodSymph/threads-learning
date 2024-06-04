package org.example;

public class Constants {

    private static final int FROM_NUMBER_FIRST_THREAD = 1;

    private static final int TO_NUMBER_FIRST_THREAD = 500;

    private static final int FROM_NUMBER_SECOND_THREAD = 501;

    private static final int TO_NUMBER_SECOND_THREAD = 1000;

    private static final int SECONDS_SLEEP = 1000;


    public static int getFROM_NUMBER_FIRST_THREAD() {
        return FROM_NUMBER_FIRST_THREAD;
    }

    public static int getTO_NUMBER_FIRST_THREAD() {
        return TO_NUMBER_FIRST_THREAD;
    }

    public static int getFROM_NUMBER_SECOND_THREAD() {
        return FROM_NUMBER_SECOND_THREAD;
    }

    public static int getTO_NUMBER_SECOND_THREAD() {
        return TO_NUMBER_SECOND_THREAD;
    }

    public static int getSecondsSleep() {
        return SECONDS_SLEEP;
    }

}
