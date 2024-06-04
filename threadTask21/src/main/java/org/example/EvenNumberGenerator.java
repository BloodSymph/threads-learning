package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public final class EvenNumberGenerator {

    private static final int GENERATION_DELTA = 2;

    private final AtomicInteger value = new AtomicInteger();

    public int generate() {
        return this.value.getAndAdd(GENERATION_DELTA);
    }

    public int getValue() {
        return this.value.intValue();
    }

}
