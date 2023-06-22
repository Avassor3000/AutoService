package com.example.autoservice.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
