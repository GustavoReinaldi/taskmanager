package com.stefanini.taskmanager.mappers;

import com.stefanini.taskmanager.dtos.TaskDto;
import com.stefanini.taskmanager.entities.StatusEntity;
import com.stefanini.taskmanager.entities.TaskEntity;

public class TaskEntityToDtoMapper implements DefaultMapper<TaskEntity, TaskDto> {
    private static TaskEntityToDtoMapper INSTANCE;
    public static TaskEntityToDtoMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskEntityToDtoMapper();
        }
        return INSTANCE;
    }

    @Override
    public TaskEntity convert(TaskDto taskDto) {
        return TaskEntity.builder()
                .id(taskDto.getId())
                .createdAt(taskDto.getCreatedAt())
                .description(taskDto.getDescription())
                .title(taskDto.getTitle())
                .status(StatusEntity.builder()
                        .title(taskDto.getStatusName())
                        .build())
                .build();
    }

    @Override
    public TaskDto convertToDto(TaskEntity taskEntity) {
        return TaskDto.builder()
                .id(taskEntity.getId())
                .createdAt(taskEntity.getCreatedAt())
                .description(taskEntity.getDescription())
                .title(taskEntity.getTitle())
                .statusName(taskEntity.getStatus().getTitle())
                .build();
    }
}
