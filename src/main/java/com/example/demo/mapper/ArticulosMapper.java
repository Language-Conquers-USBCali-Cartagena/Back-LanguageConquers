package com.example.demo.mapper;

import com.example.demo.model.Articulos;
import com.example.demo.model.dto.ArticulosDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticulosMapper {

    ArticulosMapper INSTANCE = Mappers.getMapper(ArticulosMapper.class);
    @Mapping(target = "estado.idEstado", source = "idEstado")
    @Mapping(target = "categoria.idCategoria", source = "idCategoria")
    Articulos toEntity(ArticulosDTO articulosDTO);
    @Mapping(target = "idEstado", source = "estado.idEstado")
    @Mapping(target = "idCategoria", source = "categoria.idCategoria")
    ArticulosDTO toDTO(Articulos articulos);
    List<ArticulosDTO> toDTOList(List<Articulos> articulosList);

}
