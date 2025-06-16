package org.zr.tasks.domain.dto;

import org.zr.tasks.domain.entities.TaskPriority;
import org.zr.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
