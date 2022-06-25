package com.example.demo.mapper;

import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroMapper INSTANCE = Mappers.getMapper(GeneroMapper.class);

    //@Mapping(target = "profesor", source = "profesor.idProfesor")
    //@Mapping(target = "estudiantes", source = "estudiante.idEstudiante")
    Genero toEntity(GeneroDTO generoDTO);
    GeneroDTO toDTO(Genero genero);

    List<GeneroDTO> ToDTOList(List<Genero> generos);
}
