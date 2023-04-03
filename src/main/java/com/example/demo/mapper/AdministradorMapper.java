package com.example.demo.mapper;

import com.example.demo.model.Administrador;
import com.example.demo.model.dto.AdministradorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {

    Administrador toEntity(AdministradorDTO administradorDTO);

    AdministradorDTO toDTO(Administrador administrador);

    List<Administrador> toDTOList(List<AdministradorDTO> administradorDTOS);
}
