package com.example.demo.mapper;

import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MisionMapper {

    MisionMapper INSTANCE = Mappers.getMapper(MisionMapper.class);

    @Mapping(target = "tipoMision.idTipoMision", source = "idTipoMision")
    @Mapping(target = "nivelMision.idNivelMision", source = "idNivelMision")
    @Mapping(target = "monedas.idMoneda", source = "idMonedas")
    @Mapping(target = "curso.idCurso", source = "idCurso")
    Mision toEntity(MisionDTO misionDTO);
    @Mapping(target = "idTipoMision", source = "tipoMision.idTipoMision")
    @Mapping(target = "idNivelMision", source = "nivelMision.idNivelMision")
    @Mapping(target = "idMonedas", source = "monedas.idMoneda")
    @Mapping(target = "idCurso", source = "curso.idCurso")
    MisionDTO toDTO(Mision mision);
    List<MisionDTO> toDTOList(List<Mision> misionList);
}
