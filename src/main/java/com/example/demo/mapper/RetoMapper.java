package com.example.demo.mapper;

import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RetoMapper {
    RetoMapper INSTANCE = Mappers.getMapper(RetoMapper.class);
    @Mapping(target = "mision.idMision", source = "idMision")
    @Mapping(target = "estado.idEstado", source = "idEstado")
    @Mapping(target = "curso.idCurso", source = "idCurso")
    Reto toEntity(RetoDTO retoDTO);
    @Mapping(target = "idMision", source = "mision.idMision")
    @Mapping(target = "idEstado", source = "estado.idEstado")
    @Mapping(target = "idCurso", source = "curso.idCurso")
    RetoDTO toDTO(Reto reto);
    List<RetoDTO> toDTOList(List<Reto> retos);
    List<Reto> toEntityList(List<RetoDTO> retoDTOS);
}
