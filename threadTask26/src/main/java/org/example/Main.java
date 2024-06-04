package org.example;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser(3) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println();
                System.out.printf("Thread: %s\n", Thread.currentThread().getName());
                System.out.printf("Current phase: %d\n", phase);
                System.out.printf("Current parties: %d\n", registeredParties);
                System.out.println();
                return super.onAdvance(phase, registeredParties);
            }
        };


        final LeafTask firstLeafTask = new LeafTask(0, 5, phaser);
        final LeafTask secondLeafTask = new LeafTask(1, 3, phaser);
        final LastLeafTask firstLastLeafTask = new LastLeafTask(2, 1, phaser);

        final Subtask firstSubtask = new Subtask(0, List.of(firstLeafTask, secondLeafTask), firstLastLeafTask);

        final LeafTask thirdLeafTask = new LeafTask(2, 6, phaser);
        final LastLeafTask secondLastLeafTask = new LastLeafTask(1, 4, phaser);

        final Subtask secondSubtask = new Subtask(1, List.of(thirdLeafTask), secondLastLeafTask);

        final LastLeafTask thirdLastLeafTask = new LastLeafTask(2, 7, phaser);
        final Subtask thirdSubtask = new Subtask(2, Collections.emptyList(), thirdLastLeafTask);

        final MainTask mainTask = new MainTask(0, List.of(firstSubtask, secondSubtask, thirdSubtask));
        mainTask.perform();
    }

}