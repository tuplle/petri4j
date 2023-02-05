package dev.tuplle.petri4j.parser.json;

import dev.tuplle.petri4j.parser.ParsingException;

public class PetriNetJsonParsingException extends ParsingException {

    public PetriNetJsonParsingException() {
        super("Parsing Petri Net json has failed.");
    }

    public PetriNetJsonParsingException(Throwable e) {
        super("Parsing Petri Net json has failed.", e);
    }
}
