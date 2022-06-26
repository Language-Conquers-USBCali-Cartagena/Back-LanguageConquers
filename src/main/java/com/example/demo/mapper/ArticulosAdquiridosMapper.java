package com.example.demo.mapper;

import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticulosAdquiridosMapper {

    ArticulosAdquiridosMapper INSTANCE = Mappers.getMapper(ArticulosAdquiridosMapper.class);
    @Mapping(target = "estudiante.idEstudiante", source = "idEstudiante")
    @Mapping(target = "articulos.idArticulo", source = "idArticulos")
    ArticulosAdquiridos toEntity(ArticulosAdquiridosDTO articulosAdquiridosDTO);
    @Mapping(target = "idEstudiante", source = "estudiante.idEstudiante")
    @Mapping(target = "idArticulos", source = "articulos.idArticulo")
    ArticulosAdquiridosDTO toDTO(ArticulosAdquiridos articulosAdquiridos);

    List<ArticulosAdquiridosDTO> toDTOList(List<ArticulosAdquiridos>articulosAdquiridosList);
}
