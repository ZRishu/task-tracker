package org.zr.tasks.services;

import org.zr.tasks.domain.dto.TaskListDto;
import org.zr.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
//    TaskList updateTaskList(TaskList taskList);
}
