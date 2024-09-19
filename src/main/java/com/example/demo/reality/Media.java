package com.example.demo.reality;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;
    private MediaType type;

    @ManyToOne
    @JoinColumn(name = "fk_reality_id", referencedColumnName = "id")
    private Reality reality;
}