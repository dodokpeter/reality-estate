package com.example.demo.outputs;

import com.example.demo.domain.models.Reality;
import com.example.demo.outputs.entities.RealityEntity;
import com.example.demo.outputs.mappers.RealityOutputMapper;
import com.example.demo.outputs.repositories.RealityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RealitiesOutputAdapterTest {
    @InjectMocks
    private RealitiesOutputAdapter realitiesOutputAdapter;

    @Mock
    private RealityRepository realityRepository;

    @Mock
    private RealityOutputMapper realityOutputMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_return_all_realities_by_price_interval() {
        Integer minPrice = null;
        Integer maxPrice = null;
        List<RealityEntity> realityEntityList = new ArrayList<>();
        List<Reality> realityList = new ArrayList<>();

        Mockito.when(realityRepository.findAll()).thenReturn(realityEntityList);
        Mockito.when(realityOutputMapper.mapRealityEntityListToRealityList(realityEntityList)).thenReturn(realityList);

        List<Reality> fetchedRealityList = realitiesOutputAdapter.getRealities(minPrice, maxPrice);

        assertNotNull(fetchedRealityList);
    }

    @Test
    void should_return_some_realities_by_price_interval() {
        Integer minPrice = 0;
        Integer maxPrice = 20000;
        List<RealityEntity> realityEntityList = new ArrayList<>();
        List<Reality> realityList = new ArrayList<>();

        Mockito.when(realityRepository.findRealityEntityByPriceInterval(minPrice, maxPrice)).thenReturn(realityEntityList);
        Mockito.when(realityOutputMapper.mapRealityEntityListToRealityList(realityEntityList)).thenReturn(realityList);

        List<Reality> fetchedRealityList = realitiesOutputAdapter.getRealities(minPrice, maxPrice);

        assertNotNull(fetchedRealityList);
    }
}