package com.example.demo.reality;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public <T> ResponseEntity<T> getRealityById(long realityId) throws RealityNotFoundException {
        for (Reality reality : realityList) {
            if (reality.getId() == realityId) {
                return (ResponseEntity<T>) ResponseEntity.ok(reality);
            }
        }

        throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
    }

    public void updateReality(Reality reality) {
        List<Reality> newRealityList = Stream.concat(realityList.stream(), Stream.of(reality)).toList();
        System.out.println(newRealityList);
    }

    // logger (like in Run)
    public void updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        List<Reality> newRealityList = new ArrayList<>();
        boolean foundReality = false;
        for (Reality r : realityList) {
            // add the element to list if it's not the same id, add the new reality instead if it's the same id
            if (!r.getId().equals(realityId)) {
               newRealityList = Stream.concat(newRealityList.stream(), Stream.of(r)).toList();
            }
            else {
                newRealityList = Stream.concat(newRealityList.stream(), Stream.of(reality)).toList();
                foundReality = true;
            }
        }
        if (!foundReality) {
            throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
        }

        System.out.println(newRealityList);
    }
}
