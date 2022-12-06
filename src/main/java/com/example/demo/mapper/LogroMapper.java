package com.example.demo.mapper;

import com.example.demo.model.Logro;
import com.example.demo.model.dto.LogroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(componentModel = "spring")
public interface LogroMapper {

    LogroMapper INSTANCE = Mappers.getMapper(LogroMapper.class);

    Logro toEntity (LogroDTO logroDTO);

    LogroDTO toDTO (Logro logro);

    List<LogroDTO> toDTOList (List<Logro> logros);

    List<LogroDTO> toDTOList (Page<Logro> logroPage);


}
