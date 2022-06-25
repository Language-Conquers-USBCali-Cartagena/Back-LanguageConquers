package com.example.demo.mapper;

import com.example.demo.model.TipoMision;
import com.example.demo.model.dto.TipoMisionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMisionMapper {

    TipoMisionMapper INSTANCE = Mappers.getMapper(TipoMisionMapper.class);
    TipoMision toEntity(TipoMisionDTO tipoMisionDTO);
    TipoMisionDTO toDTO(TipoMision tipoMision);
    List<TipoMisionDTO> toDTOList(List<TipoMision> tipoMisions);
}
