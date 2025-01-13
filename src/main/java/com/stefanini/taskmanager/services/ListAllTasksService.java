package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.dtos.TaskDto;

import java.util.List;

public interface ListAllTasksService {
    List<TaskDto> listAll();
}
