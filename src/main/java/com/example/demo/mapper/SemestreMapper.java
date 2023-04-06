package com.example.demo.mapper;

import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemestreMapper {

    SemestreMapper INSTANCE = Mappers.getMapper(SemestreMapper.class);

    Semestre toEntity(SemestreDTO semestreDTO);
    SemestreDTO toDTO(Semestre semestre);
    List<SemestreDTO> toDTOList(List<Semestre> semestres);
    List<Semestre> toEntityList(List<SemestreDTO> semestreDTOS);
}
