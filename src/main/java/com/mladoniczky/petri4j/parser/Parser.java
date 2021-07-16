package com.mladoniczky.petri4j.parser;

import com.mladoniczky.petri4j.Net;

import java.io.File;
import java.io.InputStream;

public interface Parser {

    Net parse(String filename);
    Net parse(InputStream inputStream);
    Net parse(File file);

}
