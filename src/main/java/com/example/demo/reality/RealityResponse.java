package com.example.demo.reality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// todo: to record

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealityResponse {
    private Long id;
    private String type;
    private String location;
    private int price;
    private int rooms;
    private int area;
    private String description;
    private List<MediaResponse> medias;

    @Override
    public String toString() {
        return "RealityResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", medias=" + medias +
                '}';
    }
}
