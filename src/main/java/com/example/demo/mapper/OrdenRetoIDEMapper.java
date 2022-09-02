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


    @Mapping(source = "idPalabraReservada", target = "palabrasReservadas.idPalabraReservada")
    @Mapping(source = "idReto", target = "reto.idReto")
    OrdenRetoIDE toEntity(OrdenRetoIDEDTO ordenRetoIDEDTO);
    @Mapping(source = "palabrasReservadas.idPalabraReservada", target = "idPalabraReservada")
    OrdenRetoIDEDTO toDTO(OrdenRetoIDE ordenRetoIDE);

    List<OrdenRetoIDEDTO> toDTOList(List<OrdenRetoIDE> ordenRetoIDES);





}
