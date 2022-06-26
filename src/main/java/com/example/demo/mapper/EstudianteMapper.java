package com.example.demo.mapper;

import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {

    EstudianteMapper INSTANCE = Mappers.getMapper(EstudianteMapper.class);


    @Mapping(target = "semestre.idSemestre", source = "idSemestre")
    @Mapping(target = "programa.idPrograma", source = "idPrograma")
    @Mapping(target = "genero.idGenero", source = "idGenero")
    @Mapping(target = "estado.idEstado", source = "idEstado")
    @Mapping(target = "avatar.idAvatar", source = "idAvatar")
    Estudiante toEntity(EstudianteDTO estudianteDTO);

    @Mapping(target = "idSemestre", source = "semestre.idSemestre")
    @Mapping(target = "idPrograma", source = "programa.idPrograma")
    @Mapping(target = "idGenero", source = "genero.idGenero")
    @Mapping(target = "idEstado", source = "estado.idEstado")
    @Mapping(target = "idAvatar", source = "avatar.idAvatar")
    EstudianteDTO toDTO(Estudiante estudiante);
    List<EstudianteDTO> toDTOList(List<Estudiante> estudianteList);
}
