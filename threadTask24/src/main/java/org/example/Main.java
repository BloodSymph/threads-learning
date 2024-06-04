package org.example;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        final int subtaskCountInMainTask = 2;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(
                subtaskCountInMainTask,
                () -> System.out.println("*************")
        );

        final LeafTask firstLeafTask = new LeafTask(0, 5, cyclicBarrier);
        final LeafTask secondLeafTask = new LeafTask(1, 3, cyclicBarrier);
        final LeafTask theardLeafTask = new LeafTask(2, 1, cyclicBarrier);

        final Subtask firstSubtask = new Subtask(
                0,
                List.of(firstLeafTask, secondLeafTask, theardLeafTask)
        );

        final LeafTask foursLeafTask = new LeafTask(3, 6, cyclicBarrier);
        final LeafTask fifthLeafTask = new LeafTask(4, 4, cyclicBarrier);
        final LeafTask sixthLeafTask = new LeafTask(5, 2, cyclicBarrier);

        final Subtask secondSubtask = new Subtask(
                1,
                List.of(foursLeafTask, fifthLeafTask, sixthLeafTask)
        );

        final MainTask mainTask = new MainTask(
                0,
                List.of(firstSubtask, secondSubtask)
        );

        mainTask.perform();
    }
}