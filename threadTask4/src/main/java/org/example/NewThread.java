package org.example;

public class NewThread extends Thread{

    private static final int FROM = 0;

    private static final int TO = 100;

    private int number;

    public NewThread(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = FROM; i <= TO; i++) {
            System.out.println(this.number);
        }
    }
}
