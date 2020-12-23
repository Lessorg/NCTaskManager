package ua.edu.sumdu.j2se.Nikolai.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    protected ListTypes.types type;
    protected static final long serialVersionUID = 2L;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;
    public abstract String toString();
    public abstract Stream<Task> getStream();

    final public AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) throws IllegalAccessException {
        AbstractTaskList incomingTasks = TaskListFactory.createTaskList(type);

        getStream().filter(task -> task.nextTimeAfter(from) != null && task.nextTimeAfter(from).compareTo(to) <= 0).forEach(incomingTasks::add);

        return incomingTasks;
    }
}
