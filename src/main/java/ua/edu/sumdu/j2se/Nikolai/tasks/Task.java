package ua.edu.sumdu.j2se.Nikolai.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Task implements Serializable, Cloneable {

    private static final long serialVersionUID = 2L;

    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private String  title;
    private boolean active;
    private boolean repeated;

    public Task() {
        this.time = LocalDateTime.MIN;
        this.start = LocalDateTime.MIN;
        this.end = LocalDateTime.MIN;
        this.interval = 0;
        this.title = "NONE";
        this.active = false;
        this.repeated = false;
    }

    public Task(String title) {
        this.time = LocalDateTime.MIN;
        this.start = LocalDateTime.MIN;
        this.end = LocalDateTime.MIN;
        this.interval = 0;
        this.title = title;
        this.active = false;
        this.repeated = false;
    }

    public Task(String title, LocalDateTime time) throws IllegalArgumentException {
        if (time == null)
            throw new IllegalArgumentException("time is null");
        this.time = time;
        this.start = LocalDateTime.MIN;
        this.end = LocalDateTime.MIN;
        this.interval = 0;
        this.title = title;
        this.active = false;
        this.repeated = false;
    }

    public Task(String title, LocalDateTime time, boolean active) throws IllegalArgumentException {
        if (time == null)
            throw new IllegalArgumentException("time is null");
        this.time = time;
        this.start = LocalDateTime.MIN;
        this.end = LocalDateTime.MIN;
        this.interval = 0;
        this.title = title;
        this.active = active;
        this.repeated = false;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException{

        if (start == null || end == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Incorrect time");
        }
        if (interval < 0) {
            throw new IllegalArgumentException("Too small interval:  interval = " + interval);
        }
        this.time = LocalDateTime.MIN;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.title = title;
        this.active = false;
        this.repeated = true;
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval, boolean active) throws IllegalArgumentException {
        if (start == null || end == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Incorrect time: start = " + start + " end = " + end);
        }
        if (interval < 0) {
            throw new IllegalArgumentException("Negative interval:  interval = " + interval);
        }
        this.time = start;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.title = title;
        this.active = active;
        this.repeated = true;
    }

    public Task(String title, LocalDateTime time, LocalDateTime start, LocalDateTime end, int interval, boolean active)
    {
        if (time == null || start == null || end == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Incorrect time: time = " + time + " start = " + start + " end = " + end);
        }
        if (interval < 0) {
            throw new IllegalArgumentException("Negative interval:  interval = " + interval);
        }
        this.time = time;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.title = title;
        this.active = active;
        this.repeated = false;
    }

    public Task(String title, int time, int start, int end, int interval, boolean active)
            throws IllegalArgumentException{
        if (start > end || start < 0) {
            throw new IllegalArgumentException("Incorrect time: start = " + start + " end = " + end);
        }
        if (interval < 0) {
            throw new IllegalArgumentException("Negative interval:  interval = " + interval);
        }
        if (time < 0) {
            throw new IllegalArgumentException("Negative time:  time = " + interval);
        }

        this.time = LocalDateTime.now().plusSeconds(time);
        this.start = LocalDateTime.now().plusSeconds(start);
        this.end = LocalDateTime.now().plusSeconds(end);
        this.interval = interval;
        this.title = title;
        this.active = active;
        this.repeated = false;
    }

    public String getTitle() {
        return title;
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

    public LocalDateTime  getTime() {
        if (isRepeated()) {
            return start;
        }
        else{
            return time;
        }
    }

    public void setTime(LocalDateTime time) {
        if (isRepeated()) {
            start = LocalDateTime.MIN;;
            end = LocalDateTime.MIN;;
            interval = 0;
            repeated = false;
        }
        this.time = time;
    }

    public void setTime(int time) {
        if (isRepeated()) {
            start = LocalDateTime.MIN;;
            end = LocalDateTime.MIN;;
            interval = 0;
            repeated = false;
        }
        this.time = LocalDateTime.now().plusSeconds(time);
    }

    public LocalDateTime getStartTime() {
        if (isRepeated()) {
            return start;
        }
        else{
            return time;
        }
    }

    public LocalDateTime getEndTime() {
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

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if(isActive()) {
            if (isRepeated()) {
                LocalDateTime temp = start;
                do
                {
                    if (current.isBefore(temp)) {
                        return temp;
                    }
                    temp = temp.plusSeconds(interval);
                }
                while (temp.compareTo(end) <= 0);
            } else if (current.isBefore(time)){
                return time;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return this.time.equals(task.time) &&
                this.title.equals(task.title) &&
                this.start.equals(task.start) &&
                this.end.equals(task.end) &&
                this.interval == task.interval &&
                this.active == task.active &&
                this.repeated == task.repeated;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode + time.toString().hashCode();
        hashCode = hashCode + start.toString().hashCode();
        hashCode = hashCode + end.toString().hashCode();
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
