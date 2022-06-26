package com.example.demo.mapper;

import com.example.demo.model.NivelMision;
import com.example.demo.model.dto.NivelMisionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NivelMisionMapper {

    NivelMisionMapper INSTNACE= Mappers.getMapper(NivelMisionMapper.class);
    NivelMision toEntity(NivelMisionDTO nivelMisionDTO);
    NivelMisionDTO toDTO(NivelMision nivelMision);
    List<NivelMisionDTO> toDTOList(List<NivelMision> nivelMisionList);
}
