package org.example;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class LetterCounter {
    private final int subtaskCount;

    public LetterCounter(final int subtaskCount) {
        this.subtaskCount = subtaskCount;
    }

    public final Map<Character, Integer> count(final String input) {
        final Map<Character, Integer> accumulator = createAccumulator();
        final Stream<SubTask> subTask = createSubtask(accumulator, input);
        execute(subTask);
        return accumulator;
    }

    protected abstract Map<Character, Integer> createAccumulator();

    protected abstract void execute(final Stream<SubTask> subTask);

    private Stream<SubTask> createSubtask(
            final Map<Character, Integer> accumulator, final String input) {
        final int subtaskCharCount = findSubtaskCharCount(input);
        return IntStream.range(0, subtaskCount).mapToObj(i -> createSubtask(accumulator, input, subtaskCharCount, i));
    }

    private int findSubtaskCharCount(final String input) {
        return  (int) Math.ceil((double)input.length() / subtaskCount);
    }

    private static SubTask createSubtask(
            final Map<Character, Integer> accumulator,
            final String input,
            final int charCount,
            final int index) {

        final int start = index * charCount;
        final int end = Math.min((index + 1) * charCount, input.length());
        return new SubTask(accumulator, input, start, end);

    }

    protected static final class SubTask {
        private final Map<Character, Integer> accumulator;
        private final String input;
        private final int start;
        private final int end;

        public SubTask(
                final Map<Character, Integer> accumulator,
                final String input,
                final int start,
                final int end) {

            this.accumulator = accumulator;
            this.input = input;
            this.start = start;
            this.end = end;
        }

        public void execute() {
            IntStream.range(start, end)
                    .map(input::codePointAt)
                    .filter(Character::isLetter)
                    .map(Character::toLowerCase)
                    .forEach(this::accumulate);
        }

        private void accumulate(final int codePoint) {
            final Character character = (char) codePoint;
            final Integer frequency = accumulator.get(character);
            if (frequency != null) {
                accumulator.put(character, frequency + 1);
            } else {
                accumulator.put(character, 1);
            }
        }
    }
}
