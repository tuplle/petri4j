package dev.tuplle.petri4j.parser;

import java.lang.reflect.InvocationTargetException;

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
        try {
            return (Parser) format.getParserClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ParsingException("Failed to initialize parser class", e);
        }
    }


}
