package com.example.demo.reality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/realities")
public class RealityController {

    private final RealityService realityService;

    @Autowired
    public RealityController(RealityService realityService) {
        this.realityService = realityService;
    }

    @GetMapping
    public List<Reality> getRealities() {
        return realityService.getRealities();
    }

    @GetMapping("/{realityId}")
    public ResponseEntity<String> getReality(@PathVariable long realityId) {
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
    public void updateReality(@RequestBody Reality reality) {
        realityService.updateReality(reality);
    }

    @PostMapping( "/{realityId}")
    public void updateReality(@RequestBody Reality reality, @PathVariable (required = false) Long realityId) {
        if (realityId == null) {
            realityService.updateReality(reality);
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
