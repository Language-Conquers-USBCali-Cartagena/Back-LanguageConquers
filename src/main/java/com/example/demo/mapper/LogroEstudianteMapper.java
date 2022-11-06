package com.example.demo.mapper;

import com.example.demo.model.LogroEstudiante;
import com.example.demo.model.dto.LogroEstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LogroEstudianteMapper {

//    LogroEstudiante INSTANCE = Mappers.getMapper(LogroEstudianteMapper.class);

    LogroEstudiante toEntity(LogroEstudianteDTO logroEstudianteDTO);
}
