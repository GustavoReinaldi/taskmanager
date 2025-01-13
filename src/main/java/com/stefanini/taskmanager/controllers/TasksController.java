package com.stefanini.taskmanager.controllers;

import com.stefanini.taskmanager.controllers.inputs.TaskInput;
import com.stefanini.taskmanager.dtos.TaskDto;
import com.stefanini.taskmanager.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private CreateTaskService createTaskService;
    private ListAllTasksService listAllTasksService;
    private GetTaskByIdService getTaskByIdService;
    private DeleteTaskByIdService deleteTaskByIdService;
    private UpdateTaskStatusService updateTaskStatusService;
    private UpdateTaskService updateTaskService;

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
        createTaskService.createTask(newTask);
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
        List<TaskDto> foundTasks = listAllTasksService.listAll();

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
        Optional<TaskDto> foundTask = getTaskByIdService.getTask(taskId);

        return (!foundTask.isEmpty())
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
            @Parameter(description = "id do novo status da tarefa") @PathVariable("new-status") Long newStatusId){
        this.updateTaskStatusService.updateTaskStatus(taskId, newStatusId);
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
            @RequestBody TaskInput input){
        this.updateTaskService.updateTaskData(taskId, input);
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
            @Parameter(description = "id da tarefa") @PathVariable("id-task") Long taskId){
        this.deleteTaskByIdService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
}
