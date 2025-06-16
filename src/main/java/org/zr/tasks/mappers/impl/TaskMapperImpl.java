package org.zr.tasks.mappers.impl;

import org.springframework.stereotype.Component;
import org.zr.tasks.domain.dto.TaskDto;
import org.zr.tasks.domain.entities.Task;
import org.zr.tasks.mappers.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new  TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getPriority()
        );
    }
}
