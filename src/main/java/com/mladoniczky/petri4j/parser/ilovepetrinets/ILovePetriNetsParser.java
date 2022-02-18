package com.mladoniczky.petri4j.parser.ilovepetrinets;

import com.mladoniczky.petri4j.Net;
import com.mladoniczky.petri4j.arc.Arc;
import com.mladoniczky.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.petri4j.arc.output.RegularOutputArc;
import com.mladoniczky.petri4j.parser.Parser;
import com.mladoniczky.petri4j.parser.ParsingException;
import com.mladoniczky.petri4j.place.Place;
import com.mladoniczky.petri4j.transition.Transition;

import java.io.*;
import java.util.function.BiFunction;

public class ILovePetriNetsParser implements Parser {

    private static final String TYPE_HEADER = ".type";
    private static final String TRANSITIONS_HEADER = ".transitions";
    private static final String PLACES_HEADER = ".places";
    private static final String ARCS_HEADER = ".arcs";

    private final BiFunction<String, Net, Object> parseTransition = (line, net) ->
            // TODO check for valid transition id/line
            new Transition(line, line);

    private final BiFunction<String, Net, Object> parsePlaces = (line, net) -> {
        String[] parts = line.split(" ");
        Integer tokens = Integer.parseInt(parts[1]);
        return new Place(parts[0], parts[0], tokens);
    };

    private final BiFunction<String, Net, Object> parseArc = (line, net) -> {
        String[] parts = line.split(" ");
        Arc arc = null;
        Long w = parts.length <= 2 ? 1L : Long.parseLong(parts[2]);
        if (net.getPlaces().containsKey(parts[0]) && net.getTransitions().containsKey(parts[1])) { //Input arc
            arc = processInputArc(net.getPlaces().get(parts[0]), net.getTransitions().get(parts[1]), w, net);
        } else if (net.getTransitions().containsKey(parts[0]) && net.getPlaces().containsKey(parts[1])) { //Output arc
            arc = processOutputArc(net.getPlaces().get(parts[1]), net.getTransitions().get(parts[0]), w, net);
        } else {
            throw new ParsingException("Could not create arcs for input: " + line);
        }
        return arc;
    };


    @Override
    public Net parse(String filename) {
        return parse(new File(filename));
    }

    @Override
    public Net parse(File file) {
        try {
            return parse(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new ParsingException("Parsing has failed!", e);
        }
    }

    @Override
    public Net parse(InputStream inputStream) {
        Net net = new Net();

        BiFunction<String, Net, Object> parsingFunction = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                if (!reader.ready()) break;
                String line = reader.readLine();

                if (line.contains(TYPE_HEADER)) {
                    if (!line.contains("pn"))
                        throw new ParsingException("Incorrect type of file. Expecting 'pn' found " + line);
                } else if (line.equals(TRANSITIONS_HEADER)) {
                    parsingFunction = parseTransition;
                    continue;
                } else if (line.equals(PLACES_HEADER)) {
                    parsingFunction = parsePlaces;
                    continue;
                } else if (line.equals(ARCS_HEADER)) {
                    parsingFunction = parseArc;
                    continue;
                } else {
                    if (parsingFunction == null) continue;
                    Object o = parsingFunction.apply(line, net);
                    if (o instanceof Transition) net.addTransition((Transition) o);
                    else if (o instanceof Place) net.addPlace((Place) o);
                    else if (o instanceof Arc) net.addArc((Arc) o);
                }
            } catch (IOException e) {
                throw new ParsingException("Parsing has failed!", e);
            }
        }

        net.makeExecutable();
        return net;
    }

    private RegularInputArc processInputArc(Place place, Transition transition, Long weight, Net net) {
        Long w = weight == null ? 1L : weight;
        RegularInputArc ia = net.getArcs().values().stream()
                .filter(RegularInputArc.class::isInstance)
                .map(RegularInputArc.class::cast)
                .filter(a -> a.getSource() == place && a.getTarget() == transition)
                .findFirst().orElse(null);
        if (ia != null) {
            ia.setWeight(ia.getWeight() + w);
            return ia;
        }
        return new RegularInputArc(place.getId() + "-" + transition.getId(), place, transition, w);
    }

    private RegularOutputArc processOutputArc(Place place, Transition transition, Long weight, Net net) {
        Long w = weight == null ? 1L : weight;
        RegularOutputArc oa = net.getArcs().values().stream()
                .filter(RegularOutputArc.class::isInstance)
                .map(RegularOutputArc.class::cast)
                .filter(a -> a.getSource() == transition && a.getTarget() == place)
                .findFirst().orElse(null);
        if (oa != null) {
            oa.setWeight(oa.getWeight() + w);
            return oa;
        }
        return new RegularOutputArc(transition.getId() + "-" + place.getId(), transition, place, w);
    }
}
