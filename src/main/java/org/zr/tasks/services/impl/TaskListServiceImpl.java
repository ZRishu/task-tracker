package org.zr.tasks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zr.tasks.domain.entities.TaskList;
import org.zr.tasks.repositories.TaskListRepository;
import org.zr.tasks.services.TaskListService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;
    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    @Transactional
    public TaskList createTaskList(TaskList taskList) {
        if(taskList.getId() != null) {
            throw new IllegalArgumentException("Task list already exists!");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Task list title can't be empty!");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    @Transactional
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (taskList.getId() == null) {
            throw new IllegalArgumentException("Task list must have an ID");
        }
        if (!Objects.equals(taskListId, taskList.getId())) {
            throw new IllegalArgumentException("Attempting to change task list ID, this is not permitted!");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found!"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    @Transactional
    public void deleteTaskListById(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
