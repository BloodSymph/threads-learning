package org.example;

import java.util.Queue;
import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

public final class ProducingTask extends ExchangingTask {

    private final ExchangedObjectFactory objectFactory;

    private final int producingObjectCount;

    public ProducingTask(Exchanger<Queue<ExchangedObject>> exchanger,
                         ExchangedObjectFactory objectFactory,
                         int producingObjectCount) {

        super(exchanger);
        this.objectFactory = objectFactory;
        this.producingObjectCount = producingObjectCount;
    }

    @Override
    protected void handel(Queue<ExchangedObject> objects) {
        IntStream.range(0, this.producingObjectCount)
                .mapToObj(i -> this.objectFactory.create())
                .peek(object -> System.out.printf("%s is being produced\n", object))
                .forEach(objects::add);
    }
}
