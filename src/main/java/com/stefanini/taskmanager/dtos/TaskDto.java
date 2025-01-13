package com.stefanini.taskmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
    private String statusName;
}
