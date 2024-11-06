package com.example.demo.domain.delegators;

import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.realities.CreateRealitiesOutputPort;
import com.example.demo.domain.ports.realities.RealitiesOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
}