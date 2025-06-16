package org.zr.tasks.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zr.tasks.domain.dto.TaskListDto;
import org.zr.tasks.domain.entities.Task;
import org.zr.tasks.domain.entities.TaskList;
import org.zr.tasks.domain.entities.TaskStatus;
import org.zr.tasks.mappers.TaskListMapper;
import org.zr.tasks.mappers.TaskMapper;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList()
                        ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }
        long closedTaskCount = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .count();
        return closedTaskCount / (double) tasks.size();
    }
}
