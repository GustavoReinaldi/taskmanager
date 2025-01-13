package com.stefanini.taskmanager.mappers;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.entities.StatusEntity;
import com.stefanini.taskmanager.entities.TaskEntity;

public class TaskInputToEntityMapper implements DefaultMapper<TaskEntity,TaskInput> {
    private static TaskInputToEntityMapper INSTANCE;
    public static TaskInputToEntityMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskInputToEntityMapper();
        }
        return INSTANCE;
    }
    @Override
    public TaskEntity convert(TaskInput taskInput) {
        return TaskEntity.builder()
                .title(taskInput.getTitle())
                .description(taskInput.getDescription())
                .status(StatusEntity.builder().id(taskInput.getIdStatus()).build())
                .build();

    }

    @Override
    public TaskInput convertToDto(TaskEntity taskEntity) {
        return null;
    }
}
