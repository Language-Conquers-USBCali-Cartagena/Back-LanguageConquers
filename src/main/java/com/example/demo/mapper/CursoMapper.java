package com.example.demo.mapper;

import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);
    @Mapping(target = "profesor.idProfesor", source = "idProfesor")
    @Mapping(target = "estado.idEstado", source = "idEstado")
    Curso toEntity(CursoDTO cursoDTO);
    @Mapping(target = "idProfesor", source = "profesor.idProfesor")
    @Mapping(target = "idEstado", source = "estado.idEstado")
    CursoDTO toDTO(Curso curso);
    List<CursoDTO> toDTOList(List<Curso> cursoList);
}
