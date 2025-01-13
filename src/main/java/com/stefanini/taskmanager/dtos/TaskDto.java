package com.stefanini.taskmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String statusName;
}
