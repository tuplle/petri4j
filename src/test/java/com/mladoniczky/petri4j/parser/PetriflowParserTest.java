package com.mladoniczky.petri4j.parser;

import com.mladoniczky.petri4j.Net;
import com.mladoniczky.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.petri4j.arc.output.RegularOutputArc;
import com.mladoniczky.petri4j.transition.TransitionState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetriflowParserTest {

    @BeforeAll
    static void setup() {

    }

    @Test
    void shouldParsePetriflow() {
        ParserFactory factory = ParserFactory.getInstance();
        Parser parser = factory.getParser(ParsingFormat.PETRIFLOW);
        Net net = parser.parse("src/test/resources/atm-withdraw.xml");

        assertNotNull(net);
        assertEquals("atm-withdraw", net.getName());
        assertEquals(11, net.getPlaces().size());
        assertEquals(6, net.getTransitions().size());
        assertEquals(24, net.getArcs().size());
        assertEquals(TransitionState.ENABLED, net.getTransitions().get("t1").getState());
        assertTrue(net.isExecutable());
        assertTrue(net.getArcs().values().stream().allMatch(a -> a instanceof RegularInputArc || a instanceof RegularOutputArc));
    }


}
