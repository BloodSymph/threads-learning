package org.example.broker;

import org.example.model.Message;

import java.util.ArrayDeque;
import java.util.Queue;

public final class MessageBroker {

    private final Queue<Message> messagesToBeConsumed;

    private final int maxAmountOfMessages;

    public MessageBroker(final int maxAmountOfMessages) {
        this.messagesToBeConsumed = new ArrayDeque<>(maxAmountOfMessages);
        this.maxAmountOfMessages = maxAmountOfMessages;
    }

    public synchronized void produce(final Message message) {
        try {
            while (this.messagesToBeConsumed.size() >= maxAmountOfMessages) {
                super.wait();
            }
            this.messagesToBeConsumed.add(message);
            super.notify();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Message consume() {
        try {
            while (this.messagesToBeConsumed.isEmpty()) {
                super.wait();
            }
            final Message consumedMessage = this.messagesToBeConsumed.poll();
            super.notify();
            return consumedMessage;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
