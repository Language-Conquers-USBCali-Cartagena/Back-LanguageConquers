package com.example.demo.mapper;

import com.example.demo.model.MisionEstudiante;
import com.example.demo.model.dto.MisionEstudianteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MisionEstudianteMapper {

    MisionEstudianteMapper INSTANCE = Mappers.getMapper(MisionEstudianteMapper.class);
    @Mapping(target = "mision.idMision", source = "idMision")
    @Mapping(target = "estudiante.idEstudiante", source = "idEstudiante")
    @Mapping(target = "estado.idEstado", source = "idEstado")
    MisionEstudiante toEntity(MisionEstudianteDTO misionEstudianteDTO);
    @Mapping(target = "idMision", source = "mision.idMision")
    @Mapping(target = "idEstudiante", source = "estudiante.idEstudiante")
    @Mapping(target = "idEstado", source = "estado.idEstado")
    MisionEstudianteDTO toDTO (MisionEstudiante misionEstudiante);

    List<MisionEstudianteDTO> toDTOList(List<MisionEstudiante> misionEstudianteList);
}
