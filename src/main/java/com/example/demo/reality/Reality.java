package com.example.demo.reality;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RealitiesTable")
public class Reality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String location;
    private int price;
    private int rooms;
    private int area;
    private String description;

    @OneToMany(mappedBy = "reality", fetch = FetchType.LAZY)
    private List<Media> medias;

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
                ", medias=" + medias +
                '}';
    }
}
