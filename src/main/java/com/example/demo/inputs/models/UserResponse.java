package com.example.demo.inputs.models;

public record UserResponse(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {}

