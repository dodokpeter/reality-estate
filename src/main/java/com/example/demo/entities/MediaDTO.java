package com.example.demo.entities;

import com.example.demo.domain.models.MediaType;

public record MediaDTO(
        String url, MediaType type
){}