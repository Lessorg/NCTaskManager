package ua.edu.sumdu.j2se.Nikolai.tasks;

public class TaskListFactory {
    static AbstractTaskList createTaskList(ListTypes.types type){

            switch(type) {
                case ARRAY:
                    return new ArrayTaskList();
                case LINKED:
                    return new LinkedTaskList();
            }
        return new ArrayTaskList();
    }
}
