package com.example.demo.mapper;

import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);




    @Mapping(target = "reto.idReto", source = "idReto")
    Rol toEntity(RolDTO rolDTO);
    @Mapping(source = "reto.idReto", target = "idReto")
    RolDTO toDTO(Rol rol);
    List<RolDTO> toDTOList(List<Rol> rolList);
}
