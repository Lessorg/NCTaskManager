package ua.edu.sumdu.j2se.Nikolai.tasks;

public class Task implements Cloneable{

    private int time;
    private int start;
    private int end;
    private int interval;
    private String  title;
    private boolean active;
    private boolean repeated;

    public Task() {
        this.time = -1;
        this.start = -1;
        this.end = -1;
        this.interval = -1;
        this.title = "NONE";
        this.active = false;
        this.repeated = false;
    }

    public Task(String title) {
        this.start = -1;
        this.end = -1;
        this.interval = -1;
        this.title = title;
        this.time = 0;
        this.active = false;
        this.repeated = false;
    }

    public Task(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Negative time:  time = " + time);
        }
        this.start = -1;
        this.end = -1;
        this.interval = -1;
        this.title = title;
        this.time = time;
        this.active = false;
        this.repeated = false;
    }

    public Task(String title, int time, boolean active) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("Negative time:  time = " + time);
        }
        this.start = -1;
        this.end = -1;
        this.interval = -1;
        this.title = title;
        this.time = time;
        this.active = active;
        this.repeated = false;
    }

    public Task(String title, int start, int end, int interval) throws IllegalArgumentException{
        if (start < 0 || start > end) {
            throw new IllegalArgumentException("Incorrect time: start = " + start + " end = " + end);
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("Negative interval:  interval = " + interval);
        }
        this.time = -1;
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    public Task(String title, int start, int end, int interval, boolean active) throws IllegalArgumentException {
        if (start < 0 || start > end) {
            throw new IllegalArgumentException("Incorrect time: start = " + start + " end = " + end);
        }
        if (interval <= 0) {
            throw new IllegalArgumentException("Negative interval:  interval = " + interval);
        }
        this.time = -1;
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = active;
        this.repeated = true;
    }

    public Task(String title, int time, int start, int end, int interval, boolean active) {
        this.time = time;
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = active;
        this.repeated = false;
    }

    public String getTitle() {
        return  title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return  active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (isRepeated()) {
            return start;
        }
        else{
            return time;
        }
    }

    public void setTime(int time) {
        if (isRepeated()) {
            start = -1;
            end = -1;
            interval = -1;
            repeated = false;
        }
        this.time = time;
    }

    public int getStartTime() {
        if (isRepeated()) {
            return start;
        }
        else{
            return time;
        }
    }

    public int getEndTime() {
        if (isRepeated()) {
            return end;
        }
        else {
            return time;
        }
    }

    public int getRepeatInterval() {
        if (isRepeated()) {
            return interval;
        }
        else {
            return 0;
        }

    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public  boolean isRepeated() {
        return repeated;
    }

    public int nextTimeAfter(int current) {
        if(isActive()) {
            if (isRepeated()) {
                int temp = start;
                do {
                    if (current < temp) {
                        return temp;
                    }
                    temp += interval;
                } while (temp <= end);
            } else if (current < time) {
                return time;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return this.time == task.time &&
                this.title.equals(task.title) &&
                this.start == task.start &&
                this.end == task.end &&
                this.interval == task.interval &&
                this.active == task.active &&
                this.repeated == task.repeated;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode + time;
        hashCode = hashCode + start;
        hashCode = hashCode + end;
        hashCode = hashCode + interval;
        hashCode = hashCode + title.hashCode();
        hashCode = hashCode + (active ? 1 : 0);
        hashCode = hashCode + (repeated ? 1 : 0);

        return hashCode;
    }

    @Override
    public String toString() {
        if(isRepeated()) {
            return("Task " + getTitle() + " time: " + getTime() + " active: " + active + "\n");
        }
        else {
            return("Task " + getTitle() + " StartTime: " + getStartTime() + " EndTime: " + getEndTime() + " Interval: " + getRepeatInterval() + " active: " + active + "\n");
        }
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        try {
            return (Task)super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new CloneNotSupportedException();
        }
    }
}
