package com.mladoniczky.petri4j.parser.petriflow;

public class PetriflowParsingException extends RuntimeException {

    public PetriflowParsingException() {
        super("Parsing petriflow process has failed.");
    }

    public PetriflowParsingException(Throwable e) {
        super("Parsing petriflow process has failed due: ", e);
    }
}
