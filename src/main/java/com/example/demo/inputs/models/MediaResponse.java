package com.example.demo.inputs.models;

import com.example.demo.domain.models.MediaType;

public record MediaResponse(
        Long id,
        String url,
        MediaType type
) {
}
