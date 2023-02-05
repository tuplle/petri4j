package dev.tuplle.petri4j.parser;

import dev.tuplle.petri4j.Net;

import java.io.File;
import java.io.InputStream;

public interface Parser {

    Net parse(String filename);
    Net parse(InputStream inputStream);
    Net parse(File file);

}
