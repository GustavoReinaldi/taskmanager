package com.stefanini.taskmanager.services;

public interface UpdateTaskStatusService {
    void updateTaskStatus(Long idTask, Long idNewStatus);
}
