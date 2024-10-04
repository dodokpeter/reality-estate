package com.example.demo.inputs;

import com.example.demo.domain.ports.CreateRealitiesInputPort;
import com.example.demo.domain.ports.UpdateRealitiesInputPort;
import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.reality.RealityMapper;
import com.example.demo.reality.RealityNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesInputPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path="api/v1/realities")
public class RealityInputAdapter {

    private final RealitiesInputPort realitiesInputPort;
    private final CreateRealitiesInputPort createRealitiesInputPort;
    private final UpdateRealitiesInputPort updateRealitiesInputPort;

    @GetMapping
    public List<RealityResponse> getRealities() {
        List<Reality> realities = realitiesInputPort.getRealities();
        return realities.parallelStream().map(reality -> new RealityResponse(
                        reality.getId(),
                        reality.getType(),
                        reality.getLocation(),
                        reality.getPrice(),
                        reality.getRooms(),
                        reality.getArea(),
                        reality.getDescription(),
                        null,
                        null
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/paginated")
    public Page<RealityResponse> getPage(Pageable page) {
        Page<Reality> realityPage = realitiesInputPort.getRealitiesByPage(page);
        List<Reality> realityList = realityPage.getContent();
        List<RealityResponse> realityResponseList = RealityMapper.mapRealityListToRealityResponseList(realityList);
        return new PageImpl<>(realityResponseList, page, realityResponseList.size());
    }

    @GetMapping("/{realityId}")
    public RealityResponse getRealityById(@PathVariable Long realityId) {
        return RealityMapper.mapRealityToRealityResponse(realitiesInputPort.getRealityById(realityId));
    }

    // todo: finish add + update

    @PostMapping()
    public void addReality(@RequestBody Reality reality) {
        createRealitiesInputPort.addReality(reality);
    }

    @PostMapping( "/{realityId}")
    public void updateReality(@RequestBody Reality reality, @PathVariable Long realityId) throws RealityNotFoundException {
        updateRealitiesInputPort.updateReality(reality, realityId);
    }
}
