package com.mladoniczky.petri4j.parser;

import com.mladoniczky.petri4j.parser.json.JsonParser;
import com.mladoniczky.petri4j.parser.petriflow.PetriflowParser;

public class ParserFactory {

    private static ParserFactory instance;

    private ParserFactory() {

    }

    public static ParserFactory getInstance() {
        if (instance == null)
            instance = new ParserFactory();
        return instance;
    }

    public Parser getParser(String parsingFormat) {
        try {
            ParsingFormat format = ParsingFormat.valueOf(parsingFormat.toUpperCase());
            return getParser(format);
        } catch (IllegalArgumentException e) {
            throw new UnsupportedOperationException(parsingFormat + "is not supported parsing format!", e);
        }
    }

    public Parser getParser(ParsingFormat format) {
        switch (format) {
            case PETRIFLOW:
                return new PetriflowParser();
            case JSON:
                return new JsonParser();
            default:
                throw new UnsupportedOperationException(format + "is not supported parsing format");
        }
    }


}
