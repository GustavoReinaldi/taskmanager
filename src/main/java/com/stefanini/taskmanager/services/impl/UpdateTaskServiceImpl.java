package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.entities.TaskEntity;
import com.stefanini.taskmanager.mappers.TaskInputToEntityMapper;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.services.UpdateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskServiceImpl implements UpdateTaskService {
    private TaskRepository taskRepository;
    private TaskInputToEntityMapper mapper;
    @Autowired
    public UpdateTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.mapper = TaskInputToEntityMapper.getInstance();
    }

    @Override
    public void updateTaskData(Long taskId, TaskInput taskInput) {
        TaskEntity updatedTask = mapper.convert(taskInput);
        updatedTask.setId(taskId);
        taskRepository.saveAndFlush(updatedTask);
    }
}
