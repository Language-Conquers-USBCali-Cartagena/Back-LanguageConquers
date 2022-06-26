package com.example.demo.mapper;

import com.example.demo.model.Grupo;
import com.example.demo.model.dto.GrupoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    GrupoMapper INSTANCE = Mappers.getMapper(GrupoMapper.class);

    Grupo toEntity(GrupoDTO grupoDTO);
    GrupoDTO toDTO(Grupo grupo);
    List<GrupoDTO> toDTOList(List<Grupo> grupos);
}
