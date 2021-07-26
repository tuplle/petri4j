package com.mladoniczky.petri4j;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NetTest {

    @BeforeAll
    static void setup() {
//        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
//        System.out.println("@BeforeEach - executes before each test method in this class");
    }

    @Test
    void shouldCreateNet() {
        Net net = new Net("Test");
        System.out.println("Created petri net with name " + net.getName());
        assertNotNull(net);
        assertEquals("Test", net.getName());
    }

}
