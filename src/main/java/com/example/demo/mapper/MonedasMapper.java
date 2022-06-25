package com.example.demo.mapper;

import com.example.demo.model.Monedas;
import com.example.demo.model.dto.MonedasDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonedasMapper {
    MonedasMapper INSTANCE = Mappers.getMapper(MonedasMapper.class);
    Monedas toEntity(MonedasDTO monedasDTO);
    MonedasDTO toDTO(Monedas monedas);
    List<MonedasDTO> toDTOList(List<Monedas> monedasList);
}
