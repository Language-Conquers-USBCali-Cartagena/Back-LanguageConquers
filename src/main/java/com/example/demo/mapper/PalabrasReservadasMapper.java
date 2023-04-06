package com.example.demo.mapper;

import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.dto.PalabrasReservadasDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PalabrasReservadasMapper {
    PalabrasReservadasMapper INSTANCE = Mappers.getMapper(PalabrasReservadasMapper.class);
    PalabrasReservadas toEntity(PalabrasReservadasDTO palabrasReservadasDTO);
    PalabrasReservadasDTO toDTO(PalabrasReservadas palabrasReservadas);
    List<PalabrasReservadasDTO> toDTOList(List<PalabrasReservadas> palabrasReservadasList);
    List<PalabrasReservadas> toEntityList(List<PalabrasReservadasDTO> palabrasReservadasDTOS);
}
