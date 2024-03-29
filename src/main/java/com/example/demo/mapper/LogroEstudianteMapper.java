package com.example.demo.mapper;

import com.example.demo.model.LogroEstudiante;
import com.example.demo.model.dto.LogroEstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogroEstudianteMapper {

    LogroEstudianteMapper INSTANCE = Mappers.getMapper(LogroEstudianteMapper.class);

    @Mapping(source ="idEstudiante" , target ="estudiante.idEstudiante")
    @Mapping(source ="idLogro" , target ="logro.idLogro")
    LogroEstudiante toEntity(LogroEstudianteDTO logroEstudianteDTO);

    List<LogroEstudianteDTO> toDTOList(List<LogroEstudiante> logroEstudianteList);
}
