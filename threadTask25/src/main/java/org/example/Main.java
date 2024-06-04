package org.example;

import java.util.Queue;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        final Exchanger<Queue<ExchangedObject>> exchanger = new Exchanger<>();

        final ExchangedObjectFactory objectFactory = new ExchangedObjectFactory();
        final int producedObjects = 3;
        final ProducingTask producingTask = new ProducingTask(
                exchanger,
                objectFactory,
                producedObjects
        );

        final ConsumingTask consumingTask = new ConsumingTask(exchanger);

        new Thread(producingTask).start();
        new Thread(consumingTask).start();
    }
}