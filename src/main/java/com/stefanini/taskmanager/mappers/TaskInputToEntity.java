package com.stefanini.taskmanager.mappers;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.entities.StatusEntity;
import com.stefanini.taskmanager.entities.TaskEntity;

public class TaskInputToEntity implements DefaultMapper<TaskEntity,TaskInput> {
    private static TaskInputToEntity INSTANCE;
    public static TaskInputToEntity getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskInputToEntity();
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
