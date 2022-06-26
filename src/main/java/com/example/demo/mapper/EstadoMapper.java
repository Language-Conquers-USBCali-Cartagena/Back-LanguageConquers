package com.example.demo.mapper;

import com.example.demo.model.Estado;
import com.example.demo.model.dto.EstadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoMapper {

    EstadoMapper INSTANCE = Mappers.getMapper(EstadoMapper.class);


    Estado toEntity(EstadoDTO estadoDTO);
    EstadoDTO toDTO(Estado estado);
    List<EstadoDTO> toDTOList(List<Estado> estadoList);
}
