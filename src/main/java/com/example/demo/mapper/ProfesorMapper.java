package com.example.demo.mapper;

import com.example.demo.model.Profesor;
import com.example.demo.model.dto.ProfesorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {

    ProfesorMapper INSTANCE = Mappers.getMapper(ProfesorMapper.class);

    @Mapping(target = "genero.idGenero", source = "idGenero")
    Profesor toEntity(ProfesorDTO profesorDTO);
    @Mapping(target = "idGenero", source = "genero.idGenero")
    ProfesorDTO toDTO(Profesor profesor);

    List<ProfesorDTO> toDTOList(List<Profesor> profesorList);
}
