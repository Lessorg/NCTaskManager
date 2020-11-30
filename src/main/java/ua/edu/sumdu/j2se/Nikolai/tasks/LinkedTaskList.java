package ua.edu.sumdu.j2se.Nikolai.tasks;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList{

    private Node head;
    private int size;

    public LinkedTaskList(){
        this.size = 0;
        this.head = null;
        type = ListTypes.types.LINKED;
    }

    private class Node {
        public Task task;
        public Node nextNode;

        Node(Task date){
            this.task = date;
            nextNode = null;
        }
    }

    @Override
    public Iterator<Task> iterator() {

        return new Iterator<Task>() {
            Node currentNode = head;
            Node previousNode = null;

            @Override
            public boolean hasNext() {
                return head.nextNode != null && currentNode != null;
            }

            @Override
            public Task next() {
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
                return previousNode.task;
            }

            @Override
            public void remove() {
                try {
                    if(previousNode == null)
                        throw new IllegalStateException("Illegal State Exception");
                    LinkedTaskList.this.remove(previousNode.task);
                }
                catch (IllegalStateException | UnsupportedOperationException e){
                    System.out.println(e.getMessage());
                    throw e;
                }
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        if(size == tasks.size){
            if (size == 0)
                return true;
            Node currentNode = head;
            for (Task task:tasks) {
                if(task.equals(currentNode.task)){
                    currentNode = currentNode.nextNode;
                }
                else {
                    return false;
                }
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
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList ResultList;
        try {
            ResultList = (LinkedTaskList)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new CloneNotSupportedException();
        }
        return ResultList;
    }

    @Override
    public void add(Task task){
        Node newNode = new Node(task);

        if (head == null){
            head = newNode;
        }
        else{
            Node currentNode = head;

            while(currentNode.nextNode != null){
                currentNode = currentNode.nextNode;
            }

            currentNode.nextNode = newNode;
        }
        size++;
    }

    @Override
    public boolean remove(Task task){

        if (head != null){
            Node currentNode = head;
            Node previousNode = head;

            for (int i = 0; i < size; i++) {
                if (task.equals(getTask(i))) {
                    if (previousNode == currentNode) {
                        head = currentNode.nextNode;
                    } else {
                        previousNode.nextNode = currentNode.nextNode;
                    }
                    size--;

                    return true;
                }
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }
        }
        return false;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException{
        if (size > index && index >= 0){

            Node currentNode = head;
            int currentIndex = 0;

            while(currentIndex != index){
                currentNode = currentNode.nextNode;
                currentIndex++;
            }
            return currentNode.task;
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
