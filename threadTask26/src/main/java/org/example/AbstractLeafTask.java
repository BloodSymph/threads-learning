package org.example;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public abstract class AbstractLeafTask extends Task {

    private final long secondDuration;

    private final Phaser phaser;

    public AbstractLeafTask(final long id, final long secondDuration, final Phaser phaser) {
        super(id);
        this.secondDuration = secondDuration;
        this.phaser = phaser;
    }

    @Override
    public final void perform() {
        try {
            System.out.printf("%s is starting\n", this);
            TimeUnit.SECONDS.sleep(secondDuration);
            System.out.printf("%s is finished\n", this);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            onFinish(phaser);
        }
    }

    protected abstract void onFinish(final Phaser phaser);

}
