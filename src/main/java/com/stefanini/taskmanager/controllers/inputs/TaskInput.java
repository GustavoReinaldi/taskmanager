package com.stefanini.taskmanager.controllers.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "TaskInput", description = "Formulário para criação e edição de tarefas")
public class TaskInput {
    @Schema(description = "Título da tarefa", example = "Título da nova tarefa")
    private String title;
    @Schema(description = "Descrição da tarefa", example = "Descrição da nova tarefa")
    private String description;
    @Schema(description = "Status da tarefa", example = "1")
    private Long idStatus;
}
