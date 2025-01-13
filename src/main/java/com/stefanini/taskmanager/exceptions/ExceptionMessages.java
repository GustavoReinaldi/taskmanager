package com.stefanini.taskmanager.exceptions;

import lombok.Getter;
@Getter
public enum ExceptionMessages {
    BAD_REQUEST("Dados inválidos. Revise os dados informados."),
    NOT_FOUND_TAREFA("Tarefa não encontrada"),
    NOT_FOUND_TAREFAS("Nenhuma tarefa para exibir"),
    INTERNAL_SERVER_ERROR("Erro inesperado do servidor. Por favor tente mais tarde");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
