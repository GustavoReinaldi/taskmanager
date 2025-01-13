package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.dtos.TaskDto;

public interface CreateTaskService {
    void createTask(TaskInput newTask);
}
