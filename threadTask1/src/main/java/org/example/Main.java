package org.example;


import static java.util.stream.IntStream.range;

public class Main {

    private final static int THREAD_AMOUNT = 10;

    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//
//
//        Thread thread = new MyThread();
//        thread.start();
//
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println("Hello thread - 1");
//            }
//        };
//        thread1.start();
//
//        RunnableThread runnableThread = new RunnableThread();
//        Thread thread2 = new Thread(runnableThread);
//        thread2.start();
//
//        Runnable runnable = () -> {
//            System.out.println("With runnable but using lambda methods!");
//        };
//        Thread thread3 = new Thread(runnable);
//        thread3.start();

        Runnable taskDisplayThreadName = () -> System.out.println(Thread.currentThread().getName());
        Runnable taskCreatingThreads = () -> range(
                0, THREAD_AMOUNT
        ).forEach(i -> start(taskDisplayThreadName));
        start(taskCreatingThreads);

    }

    public static void start(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

}