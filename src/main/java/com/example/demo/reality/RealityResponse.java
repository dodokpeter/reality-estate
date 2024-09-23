package com.example.demo.reality;
import java.util.List;

public record RealityResponse (
    Long id,
    String type,
    String location,
    int price,
    int rooms,
    int area,
    String description,
    List<MediaResponse> medias
) {}
