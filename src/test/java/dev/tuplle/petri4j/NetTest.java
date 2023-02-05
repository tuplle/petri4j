package dev.tuplle.petri4j;

import dev.tuplle.petri4j.arc.input.InhibitorArc;
import dev.tuplle.petri4j.arc.input.ReadArc;
import dev.tuplle.petri4j.arc.input.RegularInputArc;
import dev.tuplle.petri4j.arc.input.ResetArc;
import dev.tuplle.petri4j.arc.output.RegularOutputArc;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class NetTest {

    private static final String NET_ID = "n1";
    private static final String TRANSITION_ID = "t1";
    private static final String PLACE_ID = "p1";
    private static final String ARC_ID = "a1";
    private Net testNet;

    @BeforeAll
    static void setup() {
//        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
//        System.out.println("@BeforeEach - executes before each test method in this class");
        testNet = new Net(NET_ID, "Test");
        testNet.addPlace(new Place(PLACE_ID, "P1", 1));
        testNet.addTransition(new Transition(TRANSITION_ID, "T1"));
        testNet.addArc(new RegularInputArc(ARC_ID, testNet.getPlaces().get(PLACE_ID), testNet.getTransitions().get(TRANSITION_ID), 1L));
        //System.out.println("Created petri net [" + testNet.getId() + "] " + testNet.getName());
    }

    @Test
    void shouldCreateNet() {
        assertNotNull(testNet);
        assertEquals(NET_ID, testNet.getId());
        assertEquals("Test", testNet.getName());
        assertEquals(1, testNet.getPlaces().size());
        assertEquals(1, testNet.getTransitions().size());
        assertEquals(1, testNet.getArcs().size());
        assertEquals(testNet.getArcs().get(ARC_ID).getSource(), testNet.getPlaces().get(PLACE_ID));
        assertEquals(testNet.getArcs().get(ARC_ID).getTarget(), testNet.getTransitions().get(TRANSITION_ID));
    }

    @Test
    void shouldFireTransition() {
        assertNotNull(testNet);
        assertFalse(testNet.isExecutable());
        testNet.makeExecutable();
        assertTrue(testNet.isExecutable());
        assertArrayEquals(new long[]{1L}, testNet.getMarking());
        testNet.fireTransition(TRANSITION_ID);
        assertArrayEquals(new long[]{0L}, testNet.getMarking());
        assertThrows(IllegalStateException.class, () -> testNet.fireTransition(TRANSITION_ID));
    }

    @Test
    void shouldFireWithInhibitorArc() {
        testNet.addPlace(new Place("p2", "P2", 0));
        testNet.addTransition(new Transition("t2", "T2"));
        testNet.addArc(new RegularOutputArc("a2", testNet.getTransitions().get(TRANSITION_ID), testNet.getPlaces().get("p2"), 2L));
        testNet.addArc(new InhibitorArc("a3", testNet.getPlaces().get("p2"), testNet.getTransitions().get("t2"), 1L));

        assertArrayEquals(new long[]{1L,0L}, testNet.getMarking());
        testNet.fireTransition("t2");
        testNet.fireTransition(TRANSITION_ID);
        assertArrayEquals(new long[]{0L,2L}, testNet.getMarking());
        assertThrows(IllegalStateException.class,()-> testNet.fireTransition("t2"));
    }

    @Test
    void shouldFireWithReadArc() {
        testNet.addPlace(new Place("p2", "P2", 0));
        testNet.addTransition(new Transition("t2", "T2"));
        testNet.addArc(new RegularOutputArc("a2", testNet.getTransitions().get(TRANSITION_ID), testNet.getPlaces().get("p2"), 2L));
        testNet.addArc(new ReadArc("a3", testNet.getPlaces().get("p2"), testNet.getTransitions().get("t2"), 2L));

        assertArrayEquals(new long[]{1L,0L}, testNet.getMarking());
        assertThrows(IllegalStateException.class,()-> testNet.fireTransition("t2"));
        testNet.fireTransition(TRANSITION_ID);
        assertArrayEquals(new long[]{0L,2L}, testNet.getMarking());
        testNet.fireTransition("t2");
        assertArrayEquals(new long[]{0L,2L}, testNet.getMarking());
    }

    @Test
    void shouldFireWithResetArc() {
        testNet.addPlace(new Place("p2", "P2", 0));
        testNet.addTransition(new Transition("t2", "T2"));
        testNet.addArc(new RegularOutputArc("a2", testNet.getTransitions().get(TRANSITION_ID), testNet.getPlaces().get("p2"), 2L));
        testNet.addArc(new ResetArc("a3", testNet.getPlaces().get("p2"), testNet.getTransitions().get("t2")));

        assertArrayEquals(new long[]{1L,0L}, testNet.getMarking());
        testNet.fireTransition("t2");
        testNet.fireTransition(TRANSITION_ID);
        assertArrayEquals(new long[]{0L,2L}, testNet.getMarking());
        testNet.fireTransition("t2");
        assertArrayEquals(new long[]{0L,0L}, testNet.getMarking());
    }

}
