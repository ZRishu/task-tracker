package org.zr.tasks.mappers;

import org.zr.tasks.domain.dto.TaskListDto;
import org.zr.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
