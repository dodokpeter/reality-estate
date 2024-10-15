package com.example.demo.outputs.entities;

import com.example.demo.domain.models.MediaType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MediaTable")

public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private MediaType mediaType;

    @ManyToOne()
    @JoinColumn(name = "realityId", referencedColumnName = "id")
    private RealityEntity realityEntity;

    public MediaEntity(Long id, String url, MediaType mediaType) {
        this.id = id;
        this.url = url;
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "Media{" + "id=" + id + ", Url=" + url + ", MediaType=" + mediaType + '}';
    }
}
