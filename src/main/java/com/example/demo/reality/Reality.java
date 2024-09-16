package com.example.demo.reality;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

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
//    private String[] images;

    // todo: jpa repository spring boot - ako sa to cita z db
    // launch the project > controller > service > jpa.repository > findall on response
    // configure h2 not to mem but to file (so the data doesn't get erased)
    // new table - media (id, url of the photo, photo type (video / images))
    // ^ one to many
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
//                ", images=" + Arrays.toString(images) +
                '}';
    }
}
