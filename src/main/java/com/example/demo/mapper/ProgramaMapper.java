package com.example.demo.mapper;

import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramaMapper {

    ProgramaMapper INSTANCE = Mappers.getMapper(ProgramaMapper.class);
    Programa toEntity(ProgramaDTO programaDTO);
    ProgramaDTO toDTO(Programa programa);
    List<ProgramaDTO> toDTOList(List<Programa> programas);
}
