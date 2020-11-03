package ua.edu.sumdu.j2se.Nikolai.tasks;



public class ArrayTaskList {

    private Task[] taskListArray;
    private int currentSize;

    public ArrayTaskList(){
        this.taskListArray = new Task[10];
        this.currentSize = 0;
    }

    public void add(Task task){
        if(currentSize == taskListArray.length) {
            Task[] tempArray = taskListArray.clone();
            taskListArray = new Task[(int) (taskListArray.length * 1.5)];
            System.arraycopy(tempArray, 0, taskListArray, 0, tempArray.length);
        }
        taskListArray[currentSize] = task;
        currentSize++;
    }

    public boolean remove(Task task){
        for (int i = 0; i < currentSize; i++){
            if (task.equalsTask(taskListArray[i])){
                for (int j = i; j < currentSize - 1; j++){
                    taskListArray[j] = taskListArray[j + 1];
                }

                currentSize --;
                return true;
            }

        }
        return false;
    }

    public int size(){
        return this.currentSize;
    }

    public Task getTask(int index){
        if (size() > index && index >= 0){
            return taskListArray[index];
        }
        else{
            return new Task();
        }
    }

    public ArrayTaskList incoming(int from, int to){

        ArrayTaskList incomingTasks = new ArrayTaskList();

        for (int i = 0; i < size(); i++){
            int currentTime = from;
            while(taskListArray[i].nextTimeAfter(currentTime) != -1 && taskListArray[i].nextTimeAfter(from) < to) {
                if (taskListArray[i].nextTimeAfter(currentTime) <= to) {
                    incomingTasks.add(taskListArray[i]);
                    break;
                }
                currentTime = taskListArray[i].nextTimeAfter(currentTime);
            }
        }
        return incomingTasks;
    }
}
