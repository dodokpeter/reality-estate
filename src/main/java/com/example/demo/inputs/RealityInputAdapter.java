package com.example.demo.inputs;

import com.example.demo.entities.RealityEntity;
import com.example.demo.entities.RealityDTO;
import com.example.demo.reality.RealityNotFoundException;
import com.example.demo.reality.RealityService;
import domain.ports.RealitiesInputPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path="api/v1/realities")
public class RealityInputAdapter {

    private final RealitiesInputPort realitiesInputPort;

    @GetMapping
    public List<RealityDTO> getRealities() {
        return realityService.getRealities();
    }

    @GetMapping("/paginated")
    public Page<RealityDTO> getPage(Pageable page) {
        return realityService.getPage(page);
    }

    @GetMapping("/{realityId}")
    public ResponseEntity<String> getRealityById(@PathVariable Long realityId) {
        try {
            return realityService.getRealityById(realityId);
        }
        catch (RealityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
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
