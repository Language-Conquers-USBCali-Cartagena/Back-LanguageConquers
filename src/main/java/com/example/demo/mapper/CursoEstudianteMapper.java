package com.example.demo.mapper;

import com.example.demo.model.CursoEstudiante;
import com.example.demo.model.dto.CursoEstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoEstudianteMapper {

    CursoEstudianteMapper INSTANCE = Mappers.getMapper(CursoEstudianteMapper.class);
    @Mapping(target = "estudiante.idEstudiante", source = "idEstudiante")
    @Mapping(target = "curso.idCurso", source = "idCurso")
    CursoEstudiante toEntity(CursoEstudianteDTO cursoEstudianteDTO);
    @Mapping(target = "idEstudiante", source = "estudiante.idEstudiante")
    @Mapping(target = "idCurso", source = "curso.idCurso")
    CursoEstudianteDTO toDTO(CursoEstudiante cursoEstudiante);
    List<CursoEstudianteDTO> toDTOList(List<CursoEstudiante> cursoEstudianteList);
}
