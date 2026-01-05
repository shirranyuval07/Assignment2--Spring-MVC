package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanager.dao.TaskDao;
import taskmanager.entity.Task;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> getAllTasksSorted() {
        List<Task> tasks = taskDao.loadAll();
        Collections.sort(tasks);
        return tasks;
    }

    public Task getTaskById(String id) {
        return taskDao.loadAll().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void save(Task task) {
        List<Task> tasks = taskDao.loadAll();

        tasks.removeIf(t -> t.getId().equals(task.getId()));

        tasks.add(task);

        taskDao.saveAll(tasks);
    }

    public void delete(String id) {
        List<Task> tasks = taskDao.loadAll();
        tasks.removeIf(t -> t.getId().equals(id));
        taskDao.saveAll(tasks);
    }
}