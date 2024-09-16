package com.example.demo.reality;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
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
        log.info("Reality list: {}", realityList);
        return realityList;
    }

    public <T> ResponseEntity<T> getRealityById(long realityId) throws RealityNotFoundException {
        for (Reality reality : realityList) {
            if (reality.getId() == realityId) {
                log.info("Found reality with id: {}", realityId);
                return (ResponseEntity<T>) ResponseEntity.ok(reality);
            }
        }

        log.error("Could not find reality with id: {}", realityId);
        throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
    }

    public void updateReality(Reality reality) {
        List<Reality> newRealityList = Stream.concat(realityList.stream(), Stream.of(reality)).toList();
        log.info("New list of realities: {}", newRealityList);
    }

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

        log.info("Updated list of realities: {}", newRealityList);
    }
}
