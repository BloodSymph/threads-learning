package org.example;

import java.util.List;
import java.util.stream.Stream;

public final class Subtask extends CompositeTask<AbstractLeafTask> {


    public Subtask(final long id, final List<LeafTask> leafTasks, final LastLeafTask lastLeafTask) {
        super(id, concat(leafTasks, lastLeafTask));
    }

    @Override
    protected void perform(final AbstractLeafTask subtask) {
        subtask.perform();
    }

    private static List<AbstractLeafTask> concat(
            final List<LeafTask> leafTasks, final LastLeafTask lastLeafTask) {

        return Stream.concat(
                leafTasks.stream(),
                Stream.of(lastLeafTask)
        ).toList();

    }

}
