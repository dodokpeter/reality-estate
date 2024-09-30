package com.example.demo.reality;
import java.util.List;

public record RealityDTO(
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
