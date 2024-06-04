package org.example;

import java.util.List;

public final class MainTask extends CompositeTask<Subtask>{

    public MainTask(final long id, final List<Subtask> subtask) {
        super(id, subtask);
    }

    @Override
    protected void perform(final Subtask subtask) {
        new Thread(subtask::perform).start();
    }

}

