package ua.edu.sumdu.j2se.Nikolai.tasks;

public abstract class AbstractTaskList {
    protected String type;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList incomingTasks = TaskListFactory.createTaskList(ListTypes.types.valueOf(type));

        for (int i = 0; i < size(); i++){
            int currentTime = from;
            while(getTask(i).nextTimeAfter(currentTime) != -1 && getTask(i).nextTimeAfter(from) < to) {
                if (getTask(i).nextTimeAfter(currentTime) <= to) {
                    incomingTasks.add(getTask(i));
                    break;
                }
                currentTime = getTask(i).nextTimeAfter(currentTime);
            }
        }
        return incomingTasks;
    }
}
