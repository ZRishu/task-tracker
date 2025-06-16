package org.zr.tasks.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zr.tasks.domain.entities.TaskList;
import org.zr.tasks.repositories.TaskListRepository;
import org.zr.tasks.services.TaskListService;

import java.time.LocalDateTime;
import java.util.List;

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
}
