package com.example.demo.inputs.models;
import com.example.demo.entities.MediaDTO;

import java.util.List;

public record RealityResponse(
    Long id,
    String type,
    String location,
    int price,
    int rooms,
    int area,
    String description,
    List<MediaDTO> medias,
    MediaDTO previewImage
) {}
