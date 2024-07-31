package com.evgeniy.riakhin.service;

import com.evgeniy.riakhin.dao.TaskDAO;
import com.evgeniy.riakhin.domain.Status;
import com.evgeniy.riakhin.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDAO taskDAO;

    public List<Task> getOffsetLimitTasks(int offset, int limit) {
        return taskDAO.getOffsetLimitTasks(offset, limit);
    }

    public int getAllTasksCount() {
        return taskDAO.getAllTasks();
    }

    public Task createTask(String description, Status status) {
        Task task = Task.builder()
                .description(description)
                .status(status)
                .build();
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public Task editTask(int id, String description, Status status) {
        Task task = taskDAO.getByIdTask(id);
        if (isNull(task)) {
            throw new RuntimeException("Task not found in DB");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public void deleteTask(int id) {
        Task task = taskDAO.getByIdTask(id);
        if (isNull(task)) {
            throw new RuntimeException("Task not found in DB");
        }
        taskDAO.deleteTask(task);
    }
}
