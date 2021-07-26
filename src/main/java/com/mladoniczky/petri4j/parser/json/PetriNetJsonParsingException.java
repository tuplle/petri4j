package com.mladoniczky.petri4j.parser.json;

public class PetriNetJsonParsingException extends RuntimeException {

    public PetriNetJsonParsingException() {
        super("Parsing Petri Net json has failed.");
    }

    public PetriNetJsonParsingException(Throwable e) {
        super("Parsing Petri Net json has failed.", e);
    }
}
