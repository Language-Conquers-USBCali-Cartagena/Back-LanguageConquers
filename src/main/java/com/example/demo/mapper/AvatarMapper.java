package com.example.demo.mapper;

import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;


import java.util.List;


@Mapper(componentModel = "spring")
public interface AvatarMapper {

    AvatarMapper INSTANCE = Mappers.getMapper(AvatarMapper.class);

    //@Mapping(target = "estudianteList", source = "idEstudiante")

    Avatar toEntity(AvatarDTO avatarDTO);
    AvatarDTO toDTO(Avatar avatar);
    List<AvatarDTO> toDTOList(List<Avatar> listAvatar);

    List<AvatarDTO> toDTOList(Page<Avatar> listAvatar);
}
