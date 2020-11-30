package ua.edu.sumdu.j2se.Nikolai.tasks;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayTaskList extends AbstractTaskList{

    private Task[] taskListArray;
    private int currentSize;

    public ArrayTaskList(){
        this.taskListArray = new Task[10];
        this.currentSize = 0;
        type = ListTypes.types.ARRAY;
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {

            private int currentIndex = 0;
            private int previousIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && taskListArray[currentIndex] != null;
            }

            @Override
            public Task next() {
                previousIndex = currentIndex;
                return taskListArray[currentIndex++];
            }

            @Override
            public void remove() {
                try {
                    if (previousIndex == -1)
                        throw new IllegalStateException("Illegal State Exception");
                    ArrayTaskList.this.remove(taskListArray[previousIndex]);
                    currentIndex = previousIndex;
                    previousIndex = -1;
                }
                catch (UnsupportedOperationException | IllegalStateException e){
                    System.out.println(e.getMessage());
                    throw e;
                }
            }
        };
        return it;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList tasks = (ArrayTaskList) o;
        if(currentSize == tasks.currentSize){
            if (currentSize == 0)
                return true;
            for (int i = 0; i < currentSize; i++) {
                if(!taskListArray[i].equals(tasks.taskListArray[i]))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        Iterator i = iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList ResultList;
        try {
            ResultList = (ArrayTaskList)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new CloneNotSupportedException();
        }
        ResultList.taskListArray = Arrays.copyOf(taskListArray,taskListArray.length);
        return ResultList;
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
            if (task.equals(taskListArray[i])){
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

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            result.append(getTask(i).toString());
        }
        return result.toString();
    }
}
