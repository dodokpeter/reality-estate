package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import wiremock.org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// Example
@SpringBootTest
class RealityEstateApplicationTests {
    static int value = 2;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Hello setUpAll");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Hello setUp");
        value = 1;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Hello tearDown");
    }

    @Test
    void test1() {
        System.out.println("Hello test1");
        assertNotNull(new ArrayList<>());
        assertEquals(1, value);
    }

    @Test
    void contextLoads() {
        System.out.println("Hello contextLoads");
        assertNotNull(new ArrayList<>());
        assertEquals(1, value);

    }


}
