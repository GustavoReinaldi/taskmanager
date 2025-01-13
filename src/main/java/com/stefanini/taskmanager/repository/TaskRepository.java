package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Modifying()
    @Query("INSERT INTO TaskEntity (title,description, status.id) VALUES (:title,:description, :idStatus)")
    void create(@Param("title") String title,
                      @Param("description") String description,
                      @Param("idStatus") Long idStatus);

    @Query("SELECT task FROM TaskEntity task")
    List<TaskEntity> listAll ();


}
