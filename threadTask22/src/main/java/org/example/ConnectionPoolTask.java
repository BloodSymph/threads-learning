package org.example;

import java.util.concurrent.TimeUnit;

public final class ConnectionPoolTask extends AbstractPoolTask<Connection> {

    public ConnectionPoolTask(final AbstractPool<Connection> pool) {
        super(pool);
    }

    @Override
    protected void handel(final Connection connection) {
        try {
            connection.setAutoCommit(false);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
