package org.example;

import java.util.Queue;
import java.util.concurrent.Exchanger;

public final class ConsumingTask extends ExchangingTask {

    public ConsumingTask(Exchanger<Queue<ExchangedObject>> exchanger) {
        super(exchanger);
    }

    @Override
    protected void handel(Queue<ExchangedObject> objects) {
        while (!objects.isEmpty()) {
            final ExchangedObject object = objects.poll();
            System.out.printf("%s has consumed\n", object);
        }
        System.out.println("---------------------");
    }
}
