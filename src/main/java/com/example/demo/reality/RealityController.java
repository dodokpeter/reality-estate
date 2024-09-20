package com.example.demo.reality;

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
public class RealityController {

    private final RealityService realityService;

    @GetMapping
    public List<RealityResponse> getRealities() {
        return realityService.getRealities();
    }

    @GetMapping("/paginated")
    public Page<Reality> getPage(Pageable page) {
        return realityService.getRealitiesPaginated(page);
    }

    @GetMapping("/{realityId}")
    public ResponseEntity<String> getReality(@PathVariable Long realityId) {
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
    public void updateReality(@RequestBody Reality reality, @PathVariable (required = false) Long realityId) {
        if (realityId == null) {
            realityService.addReality(reality);
        }
        else {
            try {
                realityService.updateReality(reality, realityId);
            } catch (RealityNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
