package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.dtos.TaskDto;
import com.stefanini.taskmanager.entities.TaskEntity;
import com.stefanini.taskmanager.mappers.TaskEntityToDtoMapper;
import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.services.ListAllTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllTasksServiceImpl implements ListAllTasksService {
    private TaskRepository taskRepository;
    private TaskEntityToDtoMapper mapper;

    @Autowired
    public ListAllTasksServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.mapper =  TaskEntityToDtoMapper.getInstance();;
    }

    @Override
    public List<TaskDto> listAll() {
        List<TaskEntity> foundTasks = this.taskRepository.listAll();
        return  foundTasks
                .stream()
                .map(mapper::convertToDto)
                .toList();
    }
}
