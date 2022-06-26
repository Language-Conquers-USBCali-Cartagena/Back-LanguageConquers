package com.example.demo.mapper;

import com.example.demo.model.Comentario;
import com.example.demo.model.dto.ComentarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);
    @Mapping(target = "reto.idReto", source = "idReto")
    @Mapping(target = "profesor.idProfesor", source = "idProfesor")
    @Mapping(target = "estudiante.idEstudiante", source = "idEstudiante")
    Comentario toEntity(ComentarioDTO comentarioDTO);
    @Mapping(target = "idReto", source = "reto.idReto")
    @Mapping(target = "idProfesor", source = "profesor.idProfesor")
    @Mapping(target = "idEstudiante", source = "estudiante.idEstudiante")
    ComentarioDTO toDTO(Comentario comentario);
    List<ComentarioDTO> toDTOList(List<Comentario> comentarioList);
}
