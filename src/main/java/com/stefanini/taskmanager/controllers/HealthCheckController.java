package com.stefanini.taskmanager.controllers;

import com.stefanini.taskmanager.services.ApiDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health-check")
@RestController
public class HealthCheckController {
    private ApiDataService apiDataService;

    @Autowired
    public HealthCheckController(ApiDataService apiDataService) {
        this.apiDataService = apiDataService;
    }

    @Operation(
            summary = "Verificação de saúde da api - health-check",
            description = "Retorna versão e nome da api",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mensagem gerada com sucesso")
            }
    )
    @GetMapping
    public ResponseEntity<?> getApiInformation(){
        var info = this.apiDataService.getApiInformation();
        return ResponseEntity.ok(info);
    }
}
