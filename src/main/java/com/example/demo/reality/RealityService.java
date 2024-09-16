package com.example.demo.reality;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
public class RealityService {

    private final RealityRepository realityRepository;

    private List<Reality> realityList = List.of(
            new Reality(
                    123L,
                    "byt",
                    "Bratislava",
                    150000,
                    3,
                    75,
                    "Pekný 3-izbový byt v centre mesta."
//                    new String[]{
//                            "https://priklad.sk/obrazok1.jpg",
//                            "https://priklad.sk/obrazok2.jpg"
//                    }),
            ),
            new Reality(
                    124L,
                    "dom",
                    "Košice",
                    250000,
                    5,
                    150,
                    "Rodinný dom s veľkou záhradou."
//                    new String[]{
//                            "https://priklad.sk/obrazok3.jpg"
//                    }
            )
    );

    public List<Reality> getRealities() {
        log.info("Returning the list of realities ...");
        return realityRepository.findAll();
    }

    public <T> ResponseEntity<T> getRealityById(long realityId) throws RealityNotFoundException {
        log.info("Searching for the repository with the provided id ...");

        Optional<Reality> realityInDb = realityRepository.findById(realityId);
        if (realityInDb.isPresent()) {
            return (ResponseEntity<T>) ResponseEntity.ok(realityInDb);
        }

        log.error("Could not find reality with id: {}", realityId);
        throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
    }

    // add a new reality to the db
    public void updateReality(Reality reality) {
        log.info("Adding a new reality to the database ...");
        realityRepository.save(reality);
    }

    // edit an existing reality / add a new reality if not found
    public void updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        Optional<Reality> realityInDb = realityRepository.findById(realityId);
        if (realityInDb.isPresent()) {
            realityRepository.delete(realityInDb.get());
            realityInDb.ifPresent(realityRepository::delete);
            realityRepository.save(reality);
            log.info("Updated the reality with the current id.");
        }
        else {
            log.error("Could not find reality with this id.");
            throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
        }
    }
}
