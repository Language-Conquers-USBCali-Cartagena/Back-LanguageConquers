package com.example.demo.mapper;

import com.example.demo.model.PalabraReto;
import com.example.demo.model.dto.PalabraRetoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PalabraRetoMapper {
    PalabraRetoMapper INSTANCE = Mappers.getMapper(PalabraRetoMapper.class);

    @Mapping(target = "reto.idReto", source = "idReto")
    @Mapping(target = "palabrasReservadas.idPalabraReservada", source = "idPalabrasReservadas")
    PalabraReto toEntity(PalabraRetoDTO palabraRetoDTO);

    @Mapping(target = "idReto", source = "reto.idReto")
    @Mapping(target = "idPalabrasReservadas", source = "palabrasReservadas.idPalabraReservada")
    PalabraRetoDTO toDTO(PalabraReto palabraReto);

    List<PalabraRetoDTO> toDTOList(List<PalabraReto> palabraRetoList);

    List<PalabraReto> toEntityList (List<PalabraRetoDTO> palabraRetoDTOList);
}
