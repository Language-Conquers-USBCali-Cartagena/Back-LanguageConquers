package com.example.demo.mapper;

import com.example.demo.model.Credenciales;
import com.example.demo.model.dto.CredencialesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CredencialesMapper {

    CredencialesMapper INSTANCE = Mappers.getMapper(CredencialesMapper.class);

    Credenciales toEntity(CredencialesDTO credencialesDTO);
    CredencialesDTO toDTO(Credenciales credenciales);
    List<CredencialesDTO>toDTOList(List<Credenciales> credenciales);
}
