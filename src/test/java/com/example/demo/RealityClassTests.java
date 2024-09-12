package com.example.demo;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@WireMockTest(httpPort = 1234)
class RealityClassTests {

    @Test
    void testGetRealities() {
        stubFor(get("/realities").willReturn(ok()
                .withHeader("Content-Type", "application/json")
                .withBody("""
                        [
                          {
                            "id": 123,
                            "type": "byt",
                            "location": "Bratislava",
                            "price": 150000,
                            "rooms": 3,
                            "area": 75,
                            "description": "Pekný 3-izbový byt v centre mesta.",
                            "images": [
                              "https://priklad.sk/obrazok1.jpg",
                              "https://priklad.sk/obrazok2.jpg"
                            ]
                          },
                          {
                            "id": 124,
                            "type": "dom",
                            "location": "Košice",
                            "price": 250000,
                            "rooms": 5,
                            "area": 150,
                            "description": "Rodinný dom s veľkou záhradou.",
                            "images": [
                              "https://priklad.sk/obrazok3.jpg"
                            ]
                          }
                        ]
                        """)
                )
        );
    }

    @Test
    void testGetRealityById() {
        stubFor(get("/realities/123").willReturn(ok()
                .withHeader("Content-Type", "application/json")
                .withBody("""
                        {
                          "id": 123,
                          "type": "byt",
                          "location": "Bratislava",
                          "price": 150000,
                          "rooms": 3,
                          "area": 75,
                          "description": "Pekný 3-izbový byt v centre mesta.",
                          "images": [
                            "https://priklad.sk/obrazok1.jpg",
                            "https://priklad.sk/obrazok2.jpg"
                          ]
                        }
                        """)
                )
        );
    }
}
