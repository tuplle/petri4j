package com.mladoniczky.petri4j.parser.petriflow;

import com.mladoniczky.petri4j.Net;
import com.mladoniczky.petri4j.arc.Arc;
import com.mladoniczky.petri4j.arc.input.InhibitorArc;
import com.mladoniczky.petri4j.arc.input.ReadArc;
import com.mladoniczky.petri4j.arc.input.RegularInputArc;
import com.mladoniczky.petri4j.arc.input.ResetArc;
import com.mladoniczky.petri4j.arc.output.RegularOutputArc;
import com.mladoniczky.petri4j.parser.Parser;
import com.mladoniczky.petri4j.parser.petriflow.model.Document;
import com.mladoniczky.petri4j.place.Place;
import com.mladoniczky.petri4j.transition.Transition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

public class PetriflowParser implements Parser {

    @Override
    public Net parse(String fileName) throws PetriflowParsingException {
        return parse(new File(fileName));
    }

    @Override
    public Net parse(InputStream inputStream) throws PetriflowParsingException {
        try {
            Document loadedDocuments = (Document) getUnmarshaller().unmarshal(inputStream);
            return parsePetriflowDocument(loadedDocuments);
        } catch (JAXBException e) {
            throw new PetriflowParsingException(e);
        }
    }

    @Override
    public Net parse(File file) throws PetriflowParsingException {
        try {
            Document loadedDocuments = (Document) getUnmarshaller().unmarshal(file);
            return parsePetriflowDocument(loadedDocuments);
        } catch (JAXBException e) {
            throw new PetriflowParsingException(e);
        }
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
        return jaxbContext.createUnmarshaller();
    }

    private Net parsePetriflowDocument(Document document) {
        Net parsed = new Net(document.getId());
        document.getPlace().forEach(place -> parsed.addPlace(new Place(place.getId(), place.getLabel().getValue(), (long) place.getTokens())));
        document.getTransition().forEach(transition -> parsed.addTransition(new Transition(transition.getId(), transition.getLabel().getValue())));
        document.getArc().forEach(arc -> {
            Arc parsedArc = null;
            switch (arc.getType()) {
                case READ:
                    parsedArc = new ReadArc(arc.getId(), parsed.getPlaces().get(arc.getSourceId()),
                            parsed.getTransitions().get(arc.getDestinationId()), (long) arc.getMultiplicity());
                    break;
                case RESET:
                    parsedArc = new ResetArc(arc.getId(), parsed.getPlaces().get(arc.getSourceId()),
                            parsed.getTransitions().get(arc.getDestinationId()));
                    break;
                case INHIBITOR:
                    parsedArc = new InhibitorArc(arc.getId(), parsed.getPlaces().get(arc.getSourceId()),
                            parsed.getTransitions().get(arc.getDestinationId()), (long) arc.getMultiplicity());
                    break;
                case REGULAR:
                    if (isInputArc(arc, document)) {
                        parsedArc = new RegularInputArc(arc.getId(), parsed.getPlaces().get(arc.getSourceId()),
                                parsed.getTransitions().get(arc.getDestinationId()), (long) arc.getMultiplicity());
                    } else {
                        parsedArc = new RegularOutputArc(arc.getId(), parsed.getTransitions().get(arc.getSourceId()),
                                parsed.getPlaces().get(arc.getDestinationId()), (long) arc.getMultiplicity());
                    }
                    break;
                default:
//                    System.out.println("Unsupported arc type: " + arc.getType().value());
            }
            parsed.addArc(parsedArc);
        });

        parsed.makeExecutable();
        return parsed;
    }

    private boolean isInputArc(com.mladoniczky.petri4j.parser.petriflow.model.Arc arc, Document document) {
        return document.getPlace().stream().anyMatch(p -> arc.getSourceId().equalsIgnoreCase(p.getId())) &&
                document.getTransition().stream().anyMatch(t -> arc.getDestinationId().equalsIgnoreCase(t.getId()));
    }
}
