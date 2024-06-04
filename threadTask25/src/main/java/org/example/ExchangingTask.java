package org.example;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Exchanger;

public abstract class ExchangingTask implements Runnable {

    private final Exchanger<Queue<ExchangedObject>> exchanger;

    private Queue<ExchangedObject> objects;

    public ExchangingTask(
            final Exchanger<Queue<ExchangedObject>> exchanger) {
        this.exchanger = exchanger;
        this.objects = new ArrayDeque<>();
    }

    @Override
    public final void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.handel(this.objects);
            this.exchange();
        }
    }

    protected abstract void handel(final Queue<ExchangedObject> objects);

    private void exchange() {
        try {
             this.objects = this.exchanger.exchange(this.objects);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
