package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.entities.TaskEntity;
import com.stefanini.taskmanager.mappers.TaskInputToEntityMapper;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.services.CreateTaskService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CreateTaskServiceImpl implements CreateTaskService {
    private TaskRepository taskRepository;
    private TaskInputToEntityMapper inputMapper;
    @Autowired
    public CreateTaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.inputMapper = TaskInputToEntityMapper.getInstance();
    }

    @Transactional
    @Override
    public void createTask(TaskInput newTask) {
        TaskEntity entity = this.inputMapper.convert(newTask);
        taskRepository.save(entity);
    }
}
