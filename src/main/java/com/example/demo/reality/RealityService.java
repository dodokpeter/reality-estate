package com.example.demo.reality;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class RealityService {

    private final RealityRepository realityRepository;
    private final MediaRepository mediaRepository;
    private final RealityMapper realityMapper;

    // todo: delete media


    // todo: HEXAGONAL architecture
    public List<RealityDTO> getRealities() {
        log.info("Returning the list of realities ...");
        return realityMapper.manualListMapper(realityRepository.findAll());
    }

    public Page<RealityDTO> getRealitiesPaginated(Pageable page) {
        log.info("Returning the list of PAGINATED realities ...");
        Pageable realityPage = PageRequest.of(page.getPageNumber(), page.getPageSize());
        List<RealityDTO> realities = realityMapper.manualListMapper(realityRepository.findAll(realityPage).toList());
        return new PageImpl<>(realities);
    }

    public <T> ResponseEntity<T> getRealityById(long realityId) throws RealityNotFoundException {
        log.info("Searching for the repository with the provided id ...");

        Optional<? extends Reality> realityInDb = realityRepository.findById(realityId);
        if (realityInDb.isPresent()) {
            return (ResponseEntity<T>) ResponseEntity.ok(realityMapper.manualMapper(realityInDb.get()));
        }

        log.error("Could not find reality with id: {}", realityId);
        throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
    }

    // add a new reality to the db
    // todo: user class: one to one; owner of the reality (name email phone)
    @Transactional
    public void addReality(Reality reality) {
        log.info("Adding a new reality to the database ...");
        Reality realityNew = realityRepository.save(reality);
        reality.getMedias().forEach(
                media -> media.setReality(realityNew)
        );
        mediaRepository.saveAll(reality.getMedias());
    }

    // edit an existing reality / add a new reality if not found
    @Transactional
    public void updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        Optional<Reality> realityInDbOpt = realityRepository.findById(realityId);
        if (realityInDbOpt.isPresent()) {
            Reality realityInDb = realityInDbOpt.get();
            realityInDb.setId(realityId);
            realityInDb.setType(reality.getType());
            realityInDb.setLocation(reality.getLocation());
            realityInDb.setPrice(reality.getPrice());
            realityInDb.setRooms(reality.getRooms());
            realityInDb.setArea(reality.getArea());
            realityInDb.setDescription(reality.getDescription());
            log.info("Updated the reality (not the media yet) with the current id.");
        }
        else {
            log.error("Could not find reality with this id.");
            throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
        }
    }
}
