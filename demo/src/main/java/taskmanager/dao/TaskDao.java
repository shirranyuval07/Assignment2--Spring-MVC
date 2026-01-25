package taskmanager.dao;

import org.springframework.stereotype.Repository;
import taskmanager.entity.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDao {
    private static final String FILE_NAME = "tasks.dat";

    public void saveAll(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Task> loadAll() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}