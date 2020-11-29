package ua.edu.sumdu.j2se.Nikolai.tasks;

public class ArrayTaskList extends AbstractTaskList{

    private Task[] taskListArray;
    private int currentSize;

    public ArrayTaskList(){
        this.taskListArray = new Task[10];
        this.currentSize = 0;
        type = "ARRAY";
    }

    @Override
    public void add(Task task){
        if(currentSize == taskListArray.length) {
            Task[] tempArray = taskListArray.clone();
            taskListArray = new Task[(int) (taskListArray.length * 1.5)];
            System.arraycopy(tempArray, 0, taskListArray, 0, tempArray.length);
        }
        taskListArray[currentSize] = task;
        currentSize++;
    }

    @Override
    public boolean remove(Task task){
        for (int i = 0; i < currentSize; i++){
            if (task.equalsTask(taskListArray[i])){
                if (currentSize - 1 - i >= 0)
                    System.arraycopy(taskListArray, i + 1, taskListArray, i, currentSize - 1 - i);

                currentSize --;
                return true;
            }

        }
        return false;
    }

    @Override
    public int size(){
        return this.currentSize;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (size() > index && index >= 0){
            return taskListArray[index];
        }
        else{
            throw new IndexOutOfBoundsException("Index out of range: index = " + index);
        }
    }
}
