package ua.edu.sumdu.j2se.Nikolai.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) throws IllegalAccessException {

            switch(type) {
                case ARRAY:
                    return new ArrayTaskList();
                case LINKED:
                    return new LinkedTaskList();
                default:
                    throw new IllegalAccessException("Incorrect type");
            }
    }
}
