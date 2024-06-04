package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class SingleThreadLetterCounter extends LetterCounter {

    public SingleThreadLetterCounter(int subtaskCount) {
        super(subtaskCount);
    }

    @Override
    protected Map<Character, Integer> createAccumulator() {
        return new HashMap<>();
    }

    @Override
    protected void execute( final Stream<SubTask> subTask) {
        subTask.forEach(SubTask::execute);
    }
}
