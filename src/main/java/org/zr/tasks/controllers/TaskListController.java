package org.zr.tasks.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zr.tasks.domain.dto.TaskListDto;
import org.zr.tasks.domain.entities.TaskList;
import org.zr.tasks.mappers.TaskListMapper;
import org.zr.tasks.services.TaskListService;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
@RequiredArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createdTaskList);
    }
}
