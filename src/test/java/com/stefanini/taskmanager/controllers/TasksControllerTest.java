package com.stefanini.taskmanager.controllers;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.dtos.TaskDto;
import com.stefanini.taskmanager.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TasksControllerTest {
    @Mock
    private CreateTaskService createTaskService;
    @Mock
    private ListAllTasksService listAllTasksService;
    @Mock
    private GetTaskByIdService getTaskByIdService;
    @Mock
    private DeleteTaskByIdService deleteTaskByIdService;
    @Mock
    private UpdateTaskStatusService updateTaskStatusService;
    @Mock
    private UpdateTaskService updateTaskService;
    @InjectMocks
    private TasksController controller;

    @Test
    public void testTaskCreation() {
        BDDMockito.doNothing().when(createTaskService).createTask(ArgumentMatchers.any());
        var response = controller.createTask(TaskInput.builder().title("test").build());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testTaskUpdate() {
        BDDMockito.doNothing().when(updateTaskService).updateTaskData(ArgumentMatchers.any(), ArgumentMatchers.any());
        var response = controller.updateTask(1L, TaskInput.builder().title("test").build());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testTaskStatusUpdate() {
        BDDMockito.doNothing().when(updateTaskStatusService).updateTaskStatus(ArgumentMatchers.any(), ArgumentMatchers.any());
        var response = controller.changeTaskStatus(1L, 2L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteTask() {
        BDDMockito.doNothing()
                .when(deleteTaskByIdService).deleteTask(ArgumentMatchers.any());
        var response = controller.deleteTask(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getTaskById() {
        BDDMockito.when(getTaskByIdService.getTask(ArgumentMatchers.any()))
                .thenReturn(Optional.of(TaskDto.builder()
                        .id(21L)
                        .statusName("In progress")
                        .createdAt(Date.valueOf(LocalDate.now()))
                        .title("Task 1")
                        .description("Description")
                        .build()));
        var response = controller.retrieveTaskById(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getTaskByIdWhenNotFound() {
        BDDMockito.when(getTaskByIdService.getTask(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());
        var response = controller.retrieveTaskById(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void listAllTasks() {
        BDDMockito.when(listAllTasksService.listAll()).thenReturn(List.of(TaskDto.builder()
                .id(21L)
                .statusName("In progress")
                .createdAt(Date.valueOf(LocalDate.now()))
                .title("Task 1")
                .description("Description")
                .build()));
        var response = controller.getAllTasks();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void listAllTasksWhenNotFound() {
        BDDMockito.when(listAllTasksService.listAll()).thenReturn(new ArrayList<>());
        var response = controller.getAllTasks();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}