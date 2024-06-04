package org.example;

import java.util.concurrent.TimeUnit;

public final class ExchangedObjectFactory {

    private long nextId;

    public ExchangedObject create() {
        try {
            TimeUnit.SECONDS.sleep(2);
            return new ExchangedObject(this.nextId++);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
