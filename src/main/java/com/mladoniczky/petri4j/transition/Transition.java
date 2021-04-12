package com.mladoniczky.petri4j.transition;

import com.mladoniczky.petri4j.arc.input.InputArc;
import com.mladoniczky.petri4j.arc.output.OutputArc;
import com.mladoniczky.petri4j.generic.Node;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Transition extends Node {

    @Setter(AccessLevel.NONE)
    private TransitionState state;
    private Map<String, InputArc> inputArcs;
    private Map<String, OutputArc> outputArc;

    public Transition() {
        super();
        initialize();
    }

    public Transition(String id, String title) {
        super(id, title);
        initialize();
    }

    private void initialize() {
        state = TransitionState.DISABLED;
        inputArcs = new HashMap<>();
        outputArc = new HashMap<>();
    }

    public void addInputArc(InputArc arc) {
        inputArcs.put(arc.getId(), arc);
    }

    public void addOutputArc(OutputArc arc) {
        outputArc.put(arc.getId(), arc);
    }

    public boolean isEnable() {
        return state == TransitionState.ENABLED;
    }

    public boolean test() {
        if (inputArcs.isEmpty()) return true;
        if (state == TransitionState.TRIGGERED) return false;
        boolean hasEnoughResources = inputArcs.values().stream().allMatch(InputArc::test);
        state = hasEnoughResources ? TransitionState.ENABLED : TransitionState.DISABLED;
        return hasEnoughResources;
    }

    public void consume() {
        if (!isEnable())
            throw new IllegalStateException("Transition [" + this.id + "] " + this.name + " cannot consume resources because is not enabled!");
        state = TransitionState.TRIGGERED;
        inputArcs.forEach((id, arc) -> arc.moveResources());
    }

    public void produce() {
        if (state != TransitionState.TRIGGERED)
            throw new IllegalStateException("Transition [" + this.id + "] " + this.name + " cannot produce resources because is not triggered!");
        outputArc.forEach((id, arc) -> arc.moveResources());
        state = TransitionState.DISABLED;
    }

    public void makeSerializable() {
        inputArcs.clear();
        outputArc.clear();
    }
}
