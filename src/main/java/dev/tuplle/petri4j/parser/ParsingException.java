package dev.tuplle.petri4j.parser;

public class ParsingException extends RuntimeException{

    public ParsingException() {
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
