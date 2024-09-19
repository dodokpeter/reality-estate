package com.example.demo.reality;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    public List<Reality> getRealities() {
        log.info("Returning the list of realities ...");
        return realityRepository.findAll();
    }

    public Page<Reality> getRealitiesPaginated(Pageable page) {
        log.info("Returning the list of PAGINATED realities ...");
        Pageable realityPage = PageRequest.of(page.getPageNumber(), page.getPageSize());
        return realityRepository.findAll(realityPage);
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
    // todo: get media too; delete
    // todo: user class: one to one; owner of the reality (name email phone)
    public void addReality(Reality reality) {
        log.info("Adding a new reality to the database ...");
        Reality realityNew = realityRepository.save(reality);  // todo: < transactional
        reality.getMedias().forEach(
                media -> media.setReality(realityNew)
        );
        mediaRepository.saveAll(reality.getMedias());
    }

    // edit an existing reality / add a new reality if not found
    // todo 3: try out the transactional annotation > open the db connection and hold it until you exit the mehtod
    // eager vs lazy
//    @Transactional
    public void updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        Optional<Reality> realityInDbOpt = realityRepository.findById(realityId);
        if (realityInDbOpt.isPresent()) {
            reality.setId(realityInDbOpt.get().getId());
            reality = realityRepository.save(reality);  // todo: < transactional
            mediaRepository.saveAll(reality.getMedias());
            log.info("Updated the reality with the current id.");
        }
        else {
            log.error("Could not find reality with this id.");
            throw new RealityNotFoundException("Nehnuteľnosť s daným realityId nebola nájdená.");
        }
    }
}
