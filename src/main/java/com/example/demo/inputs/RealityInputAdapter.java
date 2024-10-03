package com.example.demo.inputs;

import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.reality.RealityNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesInputPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path="api/v1/realities")
public class RealityInputAdapter {

    private final RealitiesInputPort realitiesInputPort;

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
        return realitiesInputPort.getRealitiesByPage(page);
    }

    @GetMapping("/{realityId}")
    public  Reality getRealityById(@PathVariable Long realityId) {


            return realitiesInputPort.getRealityById(realityId);

    }

    @PostMapping()
    public void addReality(@RequestBody Reality reality) {
        realityService.addReality(reality);
    }

    @PostMapping( "/{realityId}")
    public void updateReality(@RequestBody Reality reality, @PathVariable Long realityId) throws RealityNotFoundException {
        realityService.updateReality(reality, realityId);
    }
}
