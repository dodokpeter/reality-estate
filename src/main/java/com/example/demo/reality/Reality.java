package com.example.demo.reality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

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
    private String[] images;

    @Override
    public String toString() {
        return "Reality{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
