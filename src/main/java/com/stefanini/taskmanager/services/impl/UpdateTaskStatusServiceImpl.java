package com.stefanini.taskmanager.services.impl;

import com.stefanini.taskmanager.repository.TaskRepository;
import com.stefanini.taskmanager.services.UpdateTaskStatusService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskStatusServiceImpl implements UpdateTaskStatusService {
    private TaskRepository taskRepository;

    @Autowired
    public UpdateTaskStatusServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    @Override
    public void updateTaskStatus(Long idTask, Long idNewStatus) {
        this.taskRepository.updateTaskStatus(idTask, idNewStatus);
    }
}
