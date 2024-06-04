package org.example;

import org.example.broker.MessageBroker;
import org.example.consumer.ConsumingTask;
import org.example.produser.ProducerTask;

public class Main {

    public static void main(String[] args) {
        final int brokerMessageAmount = 5;
        final MessageBroker messageBroker = new MessageBroker(brokerMessageAmount);

        final Thread producingThread = new Thread(new ProducerTask(messageBroker));
        final Thread consumerThread = new Thread(new ConsumingTask(messageBroker));

        producingThread.start();
        consumerThread.start();

    }

}