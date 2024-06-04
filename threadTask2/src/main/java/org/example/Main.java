package org.example;

public class Main {

    public static void main(String[] args) throws InterruptedException {

//        SummingNumbers firstTask = startSubTask(
//                Constants.getFROM_NUMBER_FIRST_THREAD(),
//                Constants.getTO_NUMBER_FIRST_THREAD()
//        );
//
//        SummingNumbers secondTask = startSubTask(
//                Constants.getFROM_NUMBER_SECOND_THREAD(),
//                Constants.getTO_NUMBER_SECOND_THREAD()
//        );

        SummingNumbers firstTask = new SummingNumbers(
                Constants.getFROM_NUMBER_FIRST_THREAD(),
                Constants.getTO_NUMBER_FIRST_THREAD()
        );

        Thread firstThread = new Thread(firstTask);
        firstThread.start();

        SummingNumbers secondTask = new SummingNumbers(
                Constants.getFROM_NUMBER_SECOND_THREAD(),
                Constants.getTO_NUMBER_SECOND_THREAD()
        );
        Thread secondThread = new Thread(secondTask);
        secondThread.start();

        taskSleep(firstThread, secondThread);

        int resultSum = firstTask.getResult() + secondTask.getResult();
        printResult(resultSum);

    }


//    public static SummingNumbers startSubTask(int fromNumber, int toNumber) {
//        SummingNumbers summingNumbers = new SummingNumbers(fromNumber, toNumber);
//        Thread thread = new Thread(summingNumbers);
//        thread.start();
//        return summingNumbers;
//    }

//    public static void taskSleep() throws InterruptedException {
//        Thread.sleep(Constants.getSecondsSleep());
//    }

    public static void taskSleep(Thread... threads) throws InterruptedException {
       for (Thread thread : threads) {
            thread.join();
       }
    }

    public static void printResult(int resultSum) {
        System.out.println(resultSum);
    }

}