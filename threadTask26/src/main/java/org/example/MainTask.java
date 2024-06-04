package org.example;

import java.util.List;

public final class MainTask extends CompositeTask<Subtask> {

    public MainTask(long id, List<Subtask> subtask) {
        super(id, subtask);
    }

    @Override
    protected void perform(Subtask subtask) {
        new Thread(subtask::perform).start();
    }

}
