package dev.tuplle.petri4j.arc.output;

import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class WeightedOutputArc extends OutputArc {

    protected Long weight;

    public WeightedOutputArc() {
        super();
        this.weight = 1L;
    }

    public WeightedOutputArc(Long weight) {
        super();
        this.weight = weight;
    }

    public WeightedOutputArc(String id, ArcType type, Transition source, Place target, Long weight) {
        super(id, type, source, target);
        this.weight = weight;
    }
}
