package com.mladoniczky.petri4j.parser;

import com.mladoniczky.petri4j.Net;
import com.mladoniczky.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.petri4j.arc.output.RegularOutputArc;
import com.mladoniczky.petri4j.transition.TransitionState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    @BeforeAll
    static void setup() {
    }

    @Test
    void shouldParseJsonPetriNet() {
        ParserFactory factory = ParserFactory.getInstance();
        Parser parser = factory.getParser(ParsingFormat.JSON);
        Net net = parser.parse("src/test/resources/example.json");

        assertNotNull(net);
        assertEquals("507f1f77bcf86cd799439011", net.getId());
        assertEquals("Your awesome net", net.getName());
        assertEquals(1, net.getPlaces().size());
        assertEquals(1, net.getTransitions().size());
        assertEquals(1, net.getArcs().size());
        assertEquals(TransitionState.ENABLED, net.getTransitions().get("T1").getState());
        assertTrue(net.isExecutable());
        assertTrue(net.getArcs().values().stream().allMatch(a -> a instanceof RegularInputArc || a instanceof RegularOutputArc));
    }

}
