package com.example.demo.outputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;
import com.example.demo.outputs.entities.MediaEntity;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.outputs.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// todo: split into reality / media / user mappers
@Mapper
public interface OutputMapper {
    Media mapMediaEntityToMedia(MediaEntity mediaEntity);
    List<Media> mapMediaEntityToMediaList(List<MediaEntity> medias);

    @Mapping(target = "mediaType", source = "media.type")
    @Mapping(target = "realityEntity", source = "media.reality")
    MediaEntity mapMediaToMediaEntity(Media media);
    List<MediaEntity> mapMediaListToMediaEntityList(List<Media> medias);

    Reality mapRealityEntityToReality(RealityEntity realityEntity);
    RealityEntity mapRealityToRealityEntity(Reality reality);
    List<Reality> mapRealityEntityListToRealityList(List<RealityEntity> realities);
    User mapUserEntityToUser(UserEntity userEntity);
    List<User> mapUserEntityListToUserList(List<UserEntity> users);
    UserEntity mapUserToUserEntity(User user);
}
