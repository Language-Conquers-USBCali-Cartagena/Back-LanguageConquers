package com.example.demo.mapper;

import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;


@Mapper(componentModel = "spring")
public interface AvatarMapper {

    AvatarMapper INSTANCE = Mappers.getMapper(AvatarMapper.class);

    Avatar toEntity(AvatarDTO avatarDTO);
    AvatarDTO toDTO(Avatar avatar);
    List<AvatarDTO> ToDTOList(List<Avatar> listAvatar);

}
