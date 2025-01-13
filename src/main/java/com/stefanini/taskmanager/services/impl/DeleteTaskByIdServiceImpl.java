package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.services.DeleteTaskByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskByIdServiceImpl implements DeleteTaskByIdService {
    private TaskRepository taskRepository;

    @Autowired
    public DeleteTaskByIdServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
