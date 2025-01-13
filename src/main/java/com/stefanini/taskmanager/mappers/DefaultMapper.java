package com.stefanini.taskmanager.mappers;

public interface DefaultMapper <T,D> {
    T convert(D d);
    D convertToDto(T t);
}
