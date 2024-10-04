package com.example.demo.outputs;

import com.example.demo.domain.ports.CreateRealitiesOutputPort;
import com.example.demo.domain.ports.UpdateRealitiesOutputPort;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.reality.RealityMapper;
import com.example.demo.outputs.repositories.RealityRepository;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesOutputPort;
import com.example.demo.reality.RealityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
@Slf4j
public class RealitiesOutputAdapter implements RealitiesOutputPort, CreateRealitiesOutputPort, UpdateRealitiesOutputPort {

    private final RealityRepository realityRepository;


    @Override
    public List<Reality> getRealities() {
        var realities = realityRepository.findAll();
        return RealityMapper.mapRealityEntityListToRealityList(realities);
    }

    @Override
    public Page<Reality> getRealitiesByPage(Pageable page) {
        Page<RealityEntity> realities = realityRepository.findAll(page);
        List<RealityEntity> realityEntityList = realities.getContent();
        List<Reality> realityList = RealityMapper.mapRealityEntityListToRealityList(realityEntityList);
        return new PageImpl<>(realityList, page, realityList.size());
    }

    @Override
    public Reality getRealityById(Long id) {
      Optional<RealityEntity> realityOptional = realityRepository.findById(id);
      if (realityOptional.isPresent()) {
         return RealityMapper.mapRealityEntityToReality(realityOptional.get());
      } else
         return null;
    }

    @Override
    public void addReality(Reality reality) {
        log.info("Adding a new reality to the database ...");
        RealityEntity realityEntity = RealityMapper.mapRealityToRealityEntity(reality);
        realityRepository.save(realityEntity);

        // todo: implement medias
//        reality.getMedias().forEach(
//                media -> media.setReality(realityNew)
//        );
//        mediaRepository.saveAll(reality.getMedias());
    }

    @Override
    public void updateReality(Reality reality, Long realityId) throws RealityNotFoundException {
        Optional<RealityEntity> realityInDbOpt = realityRepository.findById(realityId);
        if (realityInDbOpt.isPresent()) {
            RealityEntity realityInDb = realityInDbOpt.get();
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