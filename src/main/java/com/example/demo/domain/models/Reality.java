package com.example.demo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reality {
    private Long id;

    private String type;
    private String location;
    private int price;
    private int rooms;
    private int area;
    private String description;

    private List<Media> medias;
    private User owner;

    public Reality(Long id) {
        this.id = id;
    }
}
