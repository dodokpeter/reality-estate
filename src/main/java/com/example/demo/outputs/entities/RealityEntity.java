package com.example.demo.outputs.entities;

import com.example.demo.domain.models.User;
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
public class RealityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String location;
    private int price;
    private int rooms;
    private int area;
    private String description;

    @OneToMany(mappedBy = "realityEntity", fetch = FetchType.LAZY)
    private List<MediaEntity> medias;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserEntity userEntity;

    @Override
    public String toString() {
        return "RealityEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", rooms=" + rooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", medias=" + medias +
                ", userEntity=" + userEntity +
                '}';
    }
}
