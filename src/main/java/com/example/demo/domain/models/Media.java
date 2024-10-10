package com.example.demo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    private Long id;
    private String url;
    private MediaType type;
    private Reality reality;

    public Media(Long id, String url, MediaType type) {
        this.id = id;
        this.url = url;
        this.type = type;
    }
}
