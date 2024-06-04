package org.example;

public abstract class Task {

    private final long id;

    public Task(final long id) {
        this.id = id;
    }

    public abstract void  perform();

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                '}';
    }

}
