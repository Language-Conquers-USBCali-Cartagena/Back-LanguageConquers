package com.example.demo.mapper;

import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RetoEstudianteMapper {

    RetoEstudianteMapper INSTANCE = Mappers.getMapper(RetoEstudianteMapper.class);
    @Mapping(target = "rol.idRol", source = "idRol")
    @Mapping(target = "reto.idReto", source = "idReto")
    @Mapping(target = "grupo.idGrupo", source = "idGrupo")
    @Mapping(target = "estudiante.idEstudiante", source = "idEstudiante")
    @Mapping(target = "estado.idEstado", source = "idEstado")
    RetoEstudiante toEntity(RetoEstudianteDTO retoEstudianteDTO);
    @Mapping(target = "idRol", source = "rol.idRol")
    @Mapping(target = "idReto", source = "reto.idReto")
    @Mapping(target = "idGrupo", source = "grupo.idGrupo")
    @Mapping(target = "idEstudiante", source = "estudiante.idEstudiante")
    @Mapping(target = "idEstado", source = "estado.idEstado")
    RetoEstudianteDTO toDTO(RetoEstudiante retoEstudiante);
    List<RetoEstudianteDTO> toDTOList(List<RetoEstudiante> retoEstudianteList);
    List<RetoEstudiante> toEntityList(List<RetoEstudianteDTO> retoEstudianteDTOS);
}
