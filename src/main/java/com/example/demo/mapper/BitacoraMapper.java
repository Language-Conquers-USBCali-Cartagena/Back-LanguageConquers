package com.example.demo.mapper;

import com.example.demo.model.Bitacora;
import com.example.demo.model.dto.BitacoraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BitacoraMapper {

    BitacoraMapper INSTANCE = Mappers.getMapper(BitacoraMapper.class);
    Bitacora toEntity(BitacoraDTO bitacoraDTO);
    BitacoraDTO toDTO(Bitacora bitacora);
    List<BitacoraDTO> toDTOList(List<Bitacora> bitacoraList);
}
