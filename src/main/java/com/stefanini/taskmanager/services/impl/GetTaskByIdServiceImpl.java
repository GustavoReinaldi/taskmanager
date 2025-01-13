package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.dtos.TaskDto;
import com.stefanini.taskmanager.entities.TaskEntity;
import com.stefanini.taskmanager.mappers.TaskEntityToDtoMapper;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.services.GetTaskByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTaskByIdServiceImpl implements GetTaskByIdService {
    private TaskRepository taskRepository;
    private TaskEntityToDtoMapper mapper;
    @Autowired
    public GetTaskByIdServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.mapper = TaskEntityToDtoMapper.getInstance();
    }

    @Override
    public Optional<TaskDto> getTask(Long id) {
        Optional <TaskEntity> foundTask = taskRepository.findById(id);
        return foundTask.map(taskEntity -> mapper.convertToDto(taskEntity));

    }
}