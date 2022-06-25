package com.example.demo.mapper;

import com.example.demo.model.OrdenRetoIDE;
import com.example.demo.model.dto.OrdenRetoIDEDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenRetoIDEMapper {

    OrdenRetoIDEMapper INSTANCE = Mappers.getMapper(OrdenRetoIDEMapper.class);
    @Mapping(target = "reto.idReto", source = "idReto")
    @Mapping(target = "palabrasReservadas.idPalabraReservada", source = "idPalabraReservada")
    @Mapping(target = "ordenRetoIDE.idOrdenRetoIDE", source = "orden")
    OrdenRetoIDE toEntity(OrdenRetoIDEDTO ordenRetoIDEDTO);
    @Mapping(target = "idReto", source = "reto.idReto")
    @Mapping(target = "idPalabraReservada", source = "palabrasReservadas.idPalabraReservada")
    @Mapping(target = "idOrdenRetoIDEfk", source = "orden")
    OrdenRetoIDEDTO toDTO(OrdenRetoIDE ordenRetoIDE);
    List<OrdenRetoIDEDTO> toDTOList(List<OrdenRetoIDE> ordenRetoIDES);
}
