package com.stefanini.taskmanager.controllers;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.dtos.TaskDto;
import com.stefanini.taskmanager.exceptions.BadRequestException;
import com.stefanini.taskmanager.exceptions.ExceptionMessages;
import com.stefanini.taskmanager.exceptions.InternalServerErrorException;
import com.stefanini.taskmanager.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final CreateTaskService createTaskService;
    private final ListAllTasksService listAllTasksService;
    private final GetTaskByIdService getTaskByIdService;
    private final DeleteTaskByIdService deleteTaskByIdService;
    private final UpdateTaskStatusService updateTaskStatusService;
    private final UpdateTaskService updateTaskService;

    @Autowired
    public TasksController(CreateTaskService createTaskService,
                           ListAllTasksService listAllTasksService,
                           GetTaskByIdService getTaskByIdService,
                           DeleteTaskByIdService deleteTaskByIdService,
                           UpdateTaskStatusService updateTaskStatusService,
                           UpdateTaskService updateTaskService) {
        this.createTaskService = createTaskService;
        this.listAllTasksService = listAllTasksService;
        this.getTaskByIdService = getTaskByIdService;
        this.deleteTaskByIdService = deleteTaskByIdService;
        this.updateTaskStatusService = updateTaskStatusService;
        this.updateTaskService = updateTaskService;
    }

    @Operation(
            summary = "Cria tarefa",
            description = "Endpoint responsável pela criação de novas tarefas.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tarefa criada"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos. Revise os dados informados."),
                    @ApiResponse(responseCode = "500", description = "Erro inesperado do servidor. Por favor tente mais tarde"),
            }
    )
    @PostMapping
    public ResponseEntity<?> createTask(
            @RequestBody TaskInput newTask) {
        if (newTask == null || newTask.getTitle() == null)
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST.getMessage());
        try {
            createTaskService.createTask(newTask);
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ExceptionMessages.INTERNAL_SERVER_ERROR.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Listar todas as tarefas",
            description = "Endpoint responsável pela listagem de todas as tarefas cadastradas no banco de dados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefas encontradas"),
                    @ApiResponse(responseCode = "204", description = "Nenhuma tarefa para exibir"),
                    @ApiResponse(responseCode = "500", description = "Erro inesperado do servidor. Por favor tente mais tarde"),
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllTasks() {
        List<TaskDto> foundTasks;
        try {
            foundTasks = listAllTasksService.listAll();
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ExceptionMessages.INTERNAL_SERVER_ERROR.getMessage());
        }

        return (!foundTasks.isEmpty())
                ? ResponseEntity.status(HttpStatus.OK).body(foundTasks)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Endpoint responsável pela procura de tarefas através do id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Erro inesperado do servidor. Por favor tente mais tarde"),
            }
    )
    @GetMapping(value = "/{task-id}", produces = "application/json")
    public ResponseEntity<?> retrieveTaskById(
            @Parameter(description = "Id da tarefa")
            @RequestParam("task-id") Long taskId) {
        Optional<TaskDto> foundTask;
        try {
            foundTask = getTaskByIdService.getTask(taskId);
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ExceptionMessages.INTERNAL_SERVER_ERROR.getMessage());
        }
        return (foundTask.isPresent())
                ? ResponseEntity.status(HttpStatus.OK).body(foundTask)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(
            summary = "Atualizar status da tarefa",
            description = "Endpoint responsável pela atualização de status da tarefa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status atualizado"),
                    @ApiResponse(responseCode = "404", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro inesperado do servidor. Por favor tente mais tarde"),
            }
    )
    @PatchMapping("/{id-task}/status/{new-status}")
    public ResponseEntity<?> changeTaskStatus(
            @Parameter(description = "id da tarefa") @PathVariable("id-task") Long taskId,
            @Parameter(description = "id do novo status da tarefa") @PathVariable("new-status") Long newStatusId) {
        if (taskId == null || taskId <= 0 || newStatusId == null || newStatusId <= 0)
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST.getMessage());
        try {
            this.updateTaskStatusService.updateTaskStatus(taskId, newStatusId);
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ExceptionMessages.INTERNAL_SERVER_ERROR.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Atualizar dados da tarefa",
            description = "Endpoint responsável pela atualização de tarefas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefa atualizado"),
                    @ApiResponse(responseCode = "404", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro inesperado do servidor. Por favor tente mais tarde"),
            }
    )
    @PutMapping("/{id-task}")
    public ResponseEntity<?> updateTask(
            @Parameter(description = "id da tarefa") @PathVariable("id-task") Long taskId,
            @RequestBody TaskInput input) {
        if (taskId == null || taskId <= 0 || input == null)
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST.getMessage());
        try {
            this.updateTaskService.updateTaskData(taskId, input);
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ExceptionMessages.INTERNAL_SERVER_ERROR.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Deletar tarefa",
            description = "Endpoint responsável pela exclusão de tarefas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status atualizado"),
                    @ApiResponse(responseCode = "404", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro inesperado do servidor. Por favor tente mais tarde"),
            }
    )
    @DeleteMapping("/{id-task}")
    public ResponseEntity<?> deleteTask(
            @Parameter(description = "id da tarefa") @PathVariable("id-task") Long taskId) {
        if (taskId == null || taskId <= 0)
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST.getMessage());
        try {
            this.deleteTaskByIdService.deleteTask(taskId);
        } catch (Exception ex) {
            log.error(ex);
            throw new InternalServerErrorException(ExceptionMessages.INTERNAL_SERVER_ERROR.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
