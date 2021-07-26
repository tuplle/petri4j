package com.mladoniczky.petri4j.parser.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mladoniczky.petri4j.Net;
import com.mladoniczky.petri4j.arc.Arc;
import com.mladoniczky.petri4j.arc.input.InhibitorArc;
import com.mladoniczky.petri4j.arc.input.ReadArc;
import com.mladoniczky.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.petri4j.arc.input.ResetArc;
import com.mladoniczky.petri4j.arc.output.RegularOutputArc;
import com.mladoniczky.petri4j.parser.Parser;
import com.mladoniczky.petri4j.parser.json.model.PetrinetSchema;
import com.mladoniczky.petri4j.place.Place;
import com.mladoniczky.petri4j.transition.Transition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonParser implements Parser {

    private final ObjectMapper objectMapper;

    public JsonParser() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public Net parse(String filename) {
        return parse(new File(filename));
    }

    @Override
    public Net parse(InputStream inputStream) {
        try {
            PetrinetSchema json = this.objectMapper.readValue(inputStream, PetrinetSchema.class);
            return transform(json);
        } catch (IOException e) {
            throw new PetriNetJsonParsingException(e);
        }
    }

    @Override
    public Net parse(File file) {
        try {
            PetrinetSchema json = this.objectMapper.readValue(file, PetrinetSchema.class);
            return transform(json);
        } catch (IOException e) {
            throw new PetriNetJsonParsingException(e);
        }
    }

    private Net transform(PetrinetSchema petrinet) {
        Net net = new Net(petrinet.getId(), petrinet.getName());
        petrinet.getPlaces().forEach(place -> net.addPlace(new Place(place.getId(), place.getName(), place.getResources())));
        petrinet.getTransitions().forEach(transition -> net.addTransition(new Transition(transition.getId(), transition.getName())));
        petrinet.getArcs().forEach(arc -> {
            Arc parsedArc = null;
            switch (arc.getType()) {
                case READ:
                    parsedArc = new ReadArc(arc.getId(), net.getPlaces().get(arc.getSource()),
                            net.getTransitions().get(arc.getTarget()), (long) arc.getWeight());
                    break;
                case RESET:
                    parsedArc = new ResetArc(arc.getId(), net.getPlaces().get(arc.getSource()),
                            net.getTransitions().get(arc.getTarget()));
                    break;
                case INHIBITOR:
                    parsedArc = new InhibitorArc(arc.getId(), net.getPlaces().get(arc.getSource()),
                            net.getTransitions().get(arc.getTarget()), (long) arc.getWeight());
                    break;
                case REGULAR:
                    if (isInputArc(arc, petrinet)) {
                        parsedArc = new RegularInputArc(arc.getId(), net.getPlaces().get(arc.getSource()),
                                net.getTransitions().get(arc.getTarget()), (long) arc.getWeight());
                    } else {
                        parsedArc = new RegularOutputArc(arc.getId(), net.getTransitions().get(arc.getSource()),
                                net.getPlaces().get(arc.getTarget()), (long) arc.getWeight());
                    }
                    break;
                default:
//                    System.out.println("Unsupported arc type: " + arc.getType().value());
            }
            net.addArc(parsedArc);
        });

        net.makeExecutable();
        return net;
    }

    private boolean isInputArc(com.mladoniczky.petri4j.parser.json.model.Arc arc, PetrinetSchema petrinet) {
        return petrinet.getPlaces().stream().anyMatch(p -> arc.getSource().equalsIgnoreCase(p.getId())) &&
                petrinet.getTransitions().stream().anyMatch(t -> arc.getTarget().equalsIgnoreCase(t.getId()));
    }
}
