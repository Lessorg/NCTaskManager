package ua.edu.sumdu.j2se.Nikolai.tasks;

public class LinkedTaskList {

    private Node head;
    private int size;

    public LinkedTaskList(){
        this.size = 0;
        this.head = null;
    }

    static class Node {
        public Task task;
        Node nextNode;

        Node(Task date){
            this.task = date;
            nextNode = null;
        }
    }

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

    public boolean remove(Task task){

        if (head != null){
            Node currentNode = head;
            Node previousNode = head;

            for (int i = 0; i < size; i++) {
                if (task.equalsTask(getTask(i))) {
                    if (previousNode == currentNode && head.nextNode != null) {
                        head = currentNode.nextNode;
                    } else {
                        previousNode.nextNode = currentNode.nextNode;
                    }
                    size--;

                    currentNode = head;
                    int currentIndex = 0;

                    while(currentNode != null){
                        System.out.println(currentNode.task.getTitle());
                        currentNode = currentNode.nextNode;
                        currentIndex++;
                    }

                    return true;
                }
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }
        }
        return false;
    }

    public int size(){
        return this.size;
    }

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

    public LinkedTaskList incoming(int from, int to){

        LinkedTaskList incomingTasks = new LinkedTaskList();
        Node currentNode = head;

        while(currentNode.nextNode != null){
            int currentTime = from;
            while(currentNode.task.nextTimeAfter(currentTime) != -1 && currentNode.task.nextTimeAfter(from) < to) {
                if (currentNode.task.nextTimeAfter(currentTime) <= to) {
                    incomingTasks.add(currentNode.task);
                    break;
                }
                currentTime = currentNode.task.nextTimeAfter(currentTime);
            }
            currentNode = currentNode.nextNode;
        }
        return incomingTasks;
    }
}
