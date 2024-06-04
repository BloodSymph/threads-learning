package org.example.consumer;

import org.example.broker.MessageBroker;
import org.example.model.Message;

import java.util.concurrent.TimeUnit;

public final class ConsumingTask implements Runnable{

    private static final String MESSAGE_BE_CONSUMED = "Message '%s' is consumed.\n";

    private static final int SECONDS_SLEEP = 1;

    private final MessageBroker messageBroker;

    public ConsumingTask(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                TimeUnit.SECONDS.sleep(SECONDS_SLEEP);
                final Message consumedMessage = messageBroker.consume();
                System.out.printf(MESSAGE_BE_CONSUMED, consumedMessage);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
