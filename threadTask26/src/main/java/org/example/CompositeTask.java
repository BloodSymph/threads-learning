package org.example;

import java.util.List;

public abstract class CompositeTask<S extends Task> extends Task {
    private final List<S> subtask;

    public CompositeTask(final long id, final List<S> subtask) {
        super(id);
        this.subtask = subtask;
    }

    @Override
    public final void perform() {
        subtask.forEach(this::perform);
    }

    protected abstract void perform(final S subtask);
}
