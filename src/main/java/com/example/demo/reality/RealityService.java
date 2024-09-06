package com.example.demo.reality;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RealityService {
    private List<Reality> realityList = List.of(
            new Reality(
                    123L,
                    "byt",
                    "Bratislava",
                    150000,
                    3,
                    75,
                    "Pekný 3-izbový byt v centre mesta.",
                    new String[]{
                            "https://priklad.sk/obrazok1.jpg",
                            "https://priklad.sk/obrazok2.jpg"
                    }),
            new Reality(
                    124L,
                    "dom",
                    "Košice",
                    250000,
                    5,
                    150,
                    "Rodinný dom s veľkou záhradou.",
                    new String[]{
                            "https://priklad.sk/obrazok3.jpg"
                    }
            )
    );

    public List<Reality> getRealities() {
        return realityList;
    }
}
