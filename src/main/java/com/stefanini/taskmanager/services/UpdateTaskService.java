package com.stefanini.taskmanager.services;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;

public interface UpdateTaskService {
    void updateTaskData(Long taskId, TaskInput taskInput);
}
