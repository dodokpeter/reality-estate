package com.example.demo.reality;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealityService {
    private List<Reality> realityList = List.of(
            new Reality.RealityBuilder(
                    123L,
                    "byt",
                    "Bratislava",
                    150000,
                    3,
                    75
            ).setDescription("Pekný 3-izbový byt v centre mesta.")
                    .setImages(new String[]{
                     "https://priklad.sk/obrazok1.jpg",
                     "https://priklad.sk/obrazok2.jpg"
                    }).
                    build(),
            new Reality.RealityBuilder(
                    124L,
                    "dom",
                    "Košice",
                    250000,
                    5,
                    150
            ).setDescription("Rodinný dom s veľkou záhradou.")
                    .setImages(new String[]{
                            "https://priklad.sk/obrazok3.jpg"
                    })
                    .build()
    );

    public List<Reality> getRealities() {
        return realityList;
    }
}
