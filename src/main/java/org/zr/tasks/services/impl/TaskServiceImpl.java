package org.zr.tasks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zr.tasks.domain.entities.Task;
import org.zr.tasks.domain.entities.TaskList;
import org.zr.tasks.domain.entities.TaskPriority;
import org.zr.tasks.domain.entities.TaskStatus;
import org.zr.tasks.repositories.TaskListRepository;
import org.zr.tasks.repositories.TaskRepository;
import org.zr.tasks.services.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    @Transactional
    public Task createTask(UUID taskListId, Task task) {
        if (task.getId() != null) {
            throw new IllegalStateException("Task ID is already set");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalStateException("Task must have a title");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalStateException("Invalid task list ID provided"));
        LocalDateTime now = LocalDateTime.now();
        Task newTask = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );
        return taskRepository.save(newTask);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    @Transactional
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId() == null) {
            throw new IllegalStateException("Task must have an ID");
        }
        if (!Objects.equals(task.getId(), taskId)) {
            throw new IllegalStateException("Task IDs do not match");
        }
        if (task.getPriority() == null) {
            throw new IllegalStateException("Task must have a valid priority");
        }
        if (task.getStatus() == null) {
            throw new IllegalStateException("Task must have a valid status");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalStateException("Task not found!"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    @Transactional
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
