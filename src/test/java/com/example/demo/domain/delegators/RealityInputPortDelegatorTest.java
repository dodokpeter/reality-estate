package com.example.demo.domain.delegators;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.realities.CreateRealitiesOutputPort;
import com.example.demo.domain.ports.realities.RealitiesOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RealityInputPortDelegatorTest {
    @InjectMocks
    private RealityInputPortDelegator realityInputPortDelegator;

    @Mock
    private RealitiesOutputPort realitiesOutputPort;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_return_all_realities() {
        //given
        Integer minPrice = null;
        Integer maxPrice = null;
        List<Reality> realities = List.of(new Reality(1L), new Reality(2L), new Reality(3L));

        // Mock the calls
        Mockito.when(realitiesOutputPort.getRealities(minPrice, maxPrice)).thenReturn(realities);

        // When
        List<Reality> fetchRealities = realityInputPortDelegator.getRealities(minPrice, maxPrice);

        //them
        assertNotNull(fetchRealities);
        assertTrue(fetchRealities.size() > 0);
        assertEquals(realities.get(0).getId(), fetchRealities.get(0).getId());

        // Verification
        Mockito.verify(realitiesOutputPort, Mockito.times(1)).getRealities(minPrice, maxPrice);
    }

    @Test
    void should_return_all_realities_paginated() {
        Pageable page = null;
        Page<Reality> realitiesPage = new PageImpl<>(List.of(
                new Reality(1L), new Reality(2L), new Reality(3L), new Reality(4L)));

        Mockito.when(realitiesOutputPort.getRealitiesByPage(page)).thenReturn(realitiesPage);
        Page<Reality> fetchRealitiesPage = realityInputPortDelegator.getRealitiesByPage(page);

        assertNotNull(fetchRealitiesPage);
        assertTrue(fetchRealitiesPage.getTotalElements() > 0);
        Mockito.verify(realitiesOutputPort, Mockito.times(1)).getRealitiesByPage(page);
    }

    @Test
    void should_return_reality_by_id() throws RealityNotFoundException {
        Long id = 3L;
        Mockito.when(realitiesOutputPort.getRealityById(id)).thenReturn(new Reality(id));
        Reality fetchedReality = realityInputPortDelegator.getRealityById(id);

        assertNotNull(fetchedReality);
        Mockito.verify(realitiesOutputPort, Mockito.times(1)).getRealityById(id);
    }

    @Test
    void should_throw_exception_when_fetching_reality_by_id() throws RealityNotFoundException {
        Long id = 3L;
        Mockito.when(realitiesOutputPort.getRealityById(id)).thenThrow(new RealityNotFoundException("Reality with the given id does not exist"));

        assertThrows(RealityNotFoundException.class, () -> realityInputPortDelegator.getRealityById(id));
        Mockito.verify(realitiesOutputPort, Mockito.times(1)).getRealityById(id);
    }
}