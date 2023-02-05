package dev.tuplle.petri4j.parser;

import dev.tuplle.petri4j.Net;
import dev.tuplle.petri4j.arc.input.WeightedInputArc;
import dev.tuplle.petri4j.arc.output.WeightedOutputArc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ILovePetriNetsParserTest {

    @BeforeAll
    static void setup(){}

    @Test
    void shouldParseILovePetriNetsFile() {
        ParserFactory factory = ParserFactory.getInstance();
        Parser parser = factory.getParser(ParsingFormat.I_LOVE_PETRI_NETS);
        Net net = parser.parse("src/test/resources/ilovepetrinets/pn-example.txt");

        assertNotNull(net);
        assertNotNull(net.getId());
        assertEquals(6, net.getTransitions().size());
        assertEquals(7, net.getPlaces().size());
        assertEquals(17, net.getArcs().size());
        assertEquals(2L, net.getPlaces().get("p1").getResources());
        assertEquals(1L, ((WeightedOutputArc)net.getArcs().get("e-p6")).getWeight());
        assertEquals(3L, ((WeightedInputArc)net.getArcs().get("p4-e")).getWeight());

    }
}
