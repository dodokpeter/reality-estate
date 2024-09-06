package com.example.demo.reality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
