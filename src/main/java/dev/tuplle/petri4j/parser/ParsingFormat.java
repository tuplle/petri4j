package dev.tuplle.petri4j.parser;

import dev.tuplle.petri4j.parser.ilovepetrinets.ILovePetriNetsParser;
import dev.tuplle.petri4j.parser.json.JsonParser;
import dev.tuplle.petri4j.parser.petriflow.PetriflowParser;

public enum ParsingFormat {
    PETRIFLOW(PetriflowParser.class),
    JSON(JsonParser.class),
    I_LOVE_PETRI_NETS(ILovePetriNetsParser.class);

    private final Class parserClass;

    ParsingFormat(Class parserClass) {
        this.parserClass = parserClass;
    }

    public Class getParserClass() {
        return parserClass;
    }
}
