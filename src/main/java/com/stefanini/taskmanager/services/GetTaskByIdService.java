package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.dtos.TaskDto;

import java.util.Optional;

public interface GetTaskByIdService {
    Optional<TaskDto> getTask(Long id);
}
