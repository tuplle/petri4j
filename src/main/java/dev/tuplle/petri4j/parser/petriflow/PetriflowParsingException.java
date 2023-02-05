package dev.tuplle.petri4j.parser.petriflow;

import dev.tuplle.petri4j.parser.ParsingException;

public class PetriflowParsingException extends ParsingException {

    public PetriflowParsingException() {
        super("Parsing petriflow process has failed.");
    }

    public PetriflowParsingException(Throwable e) {
        super("Parsing petriflow process has failed due: ", e);
    }
}
