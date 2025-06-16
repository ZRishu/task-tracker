package org.zr.tasks.mappers;

import org.zr.tasks.domain.dto.TaskDto;
import org.zr.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
