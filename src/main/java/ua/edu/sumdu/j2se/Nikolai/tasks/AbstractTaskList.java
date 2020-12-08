package ua.edu.sumdu.j2se.Nikolai.tasks;

import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    protected ListTypes.types type;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;
    public abstract String toString();
    public abstract Stream<Task> getStream();

    final public AbstractTaskList incoming(int from, int to) throws IllegalAccessException {
        AbstractTaskList incomingTasks = TaskListFactory.createTaskList(type);

        getStream().filter(task -> task.nextTimeAfter(from) != -1 &&
                task.nextTimeAfter(to) == -1).forEach(incomingTasks::add);

        return incomingTasks;
    }
}
