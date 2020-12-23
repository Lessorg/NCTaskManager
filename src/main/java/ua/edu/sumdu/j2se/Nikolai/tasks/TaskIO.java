package ua.edu.sumdu.j2se.Nikolai.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TaskIO {

    public static void writeBinary( AbstractTaskList tasks, File file) throws IOException {

        try(FileOutputStream out = new FileOutputStream(file)){
            write(tasks, out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void readBinary( AbstractTaskList tasks, File file) throws IOException {

        try(FileInputStream in = new FileInputStream(file)){
            read(tasks, in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void write( AbstractTaskList tasks, OutputStream out) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            outputStream.writeInt(tasks.size());
            for (Task task : tasks){
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeInt(task.isActive() ? 1 : 0);
                outputStream.writeInt(task.getRepeatInterval());
                if (task.getRepeatInterval() > 0) {
                    outputStream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                }
                else {
                    outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        }
    }

    public static void read( AbstractTaskList tasks, InputStream in) throws IOException {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            int size = inputStream.readInt();
            for (int i = 0; i < size; i++){
                inputStream.readInt();
                String name = inputStream.readUTF();
                boolean active = inputStream.readInt() == 1;
                int interval = inputStream.readInt();
                if (interval > 0) {
                    long sTime = inputStream.readLong();
                    long eTime = inputStream.readLong();
                    LocalDateTime startTime =
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(sTime),
                                    ZoneId.ofOffset("UTC",ZoneOffset.UTC));
                    LocalDateTime endTime =
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(eTime),
                                    ZoneId.ofOffset("UTC",ZoneOffset.UTC));
                    tasks.add(new Task(name, startTime, endTime, interval, active));
                }
                else {
                    long time = inputStream.readLong();
                    LocalDateTime finalTime =
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(time),
                                    ZoneId.ofOffset("UTC",ZoneOffset.UTC));
                    tasks.add(new Task(name, finalTime, active));
                }
            }
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        try (BufferedWriter st = new BufferedWriter(out)) {
            Gson gson = new Gson();
            String taskJson = gson.toJson(tasks);
            st.write(taskJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        try (BufferedReader st = new BufferedReader(in)) {
            Gson gson = new Gson();
            String json = st.readLine();
            AbstractTaskList listOfTasks = gson.fromJson(
                    json,
                    TaskListFactory.createTaskList(tasks.type).getClass()
            );
            for (Task task: listOfTasks) {
                tasks.add(task);
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void writeText(AbstractTaskList tasks, File file){
        try (FileWriter st = new FileWriter(file)) {
            write(tasks, st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file){
        try (FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
