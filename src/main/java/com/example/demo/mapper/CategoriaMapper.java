package com.example.demo.mapper;

import com.example.demo.model.Categoria;
import com.example.demo.model.dto.CategoriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
    @Mapping(target = "estado.idEstado", source = "idEstado")
    Categoria toEntity(CategoriaDTO categoriaDTO);
    @Mapping(target = "idEstado", source = "estado.idEstado")
    CategoriaDTO toDTO(Categoria categoria);
    List<CategoriaDTO> toDTOList(List<Categoria> categoriaList);
}
