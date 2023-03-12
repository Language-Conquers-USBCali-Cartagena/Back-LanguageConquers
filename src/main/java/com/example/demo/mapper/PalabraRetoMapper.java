package com.example.demo.mapper;

import com.example.demo.model.PalabraReto;
import com.example.demo.model.dto.PalabraRetoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PalabraRetoMapper {
    PalabraRetoMapper INSTANCE = Mappers.getMapper(PalabraRetoMapper.class);

    PalabraReto toEntity(PalabraRetoDTO palabraRetoDTO);

    PalabraRetoDTO toDTO(PalabraReto palabraReto);

    List<PalabraRetoDTO> toDTOList(List<PalabraReto> palabraRetoList);
}
