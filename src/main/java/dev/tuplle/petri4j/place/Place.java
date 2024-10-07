package dev.tuplle.petri4j.place;

import dev.tuplle.petri4j.arc.input.InputArc;
import dev.tuplle.petri4j.arc.output.OutputArc;
import dev.tuplle.petri4j.generic.Node;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Place extends Node {

    private Long resources;
    private Map<String, OutputArc> inputArcs;
    private Map<String, InputArc> outputArcs;

    public Place() {
        super();
        initialize();
    }

    public Place(String id, String title, Long resources) {
        super(id, title);
        initialize();
        this.resources = resources;
    }

    public Place(String id, String name, Integer resources) {
        this(id, name, resources.longValue());
    }

    private void initialize() {
        resources = 0L;
        inputArcs = new HashMap<>();
        outputArcs = new HashMap<>();
    }

    public void addInputArc(OutputArc arc) {
        inputArcs.put(arc.getId(), arc);
    }

    public void addOutputArc(InputArc arc) {
        outputArcs.put(arc.getId(), arc);
    }

    public void increase(Long amount) {
        resources += amount;
    }

    public void decrease(Long amount) {
        resources -= amount;
    }

    public void makeSerializable() {
        inputArcs.clear();
        outputArcs.clear();
    }
}
