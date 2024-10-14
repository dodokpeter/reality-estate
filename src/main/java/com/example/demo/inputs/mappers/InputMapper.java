package com.example.demo.inputs.mappers;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.models.User;
import com.example.demo.inputs.models.MediaResponse;
import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.inputs.models.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InputMapper {
    MediaResponse mapMediaToMediaResponse(Media media);
    List<MediaResponse> mapMediaToMediaResponse(List<Media> medias);
    RealityResponse mapRealityToRealityResponse(Reality reality);
    List<RealityResponse> mapRealityListToRealityResponseList(List<Reality> realityList);
    UserResponse mapUserToUserResponse(User user);
    List<UserResponse> mapUserListToUserResponse(List<User> users);
}
