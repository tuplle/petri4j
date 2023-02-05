package dev.tuplle.petri4j.arc.output;

import dev.tuplle.petri4j.arc.Arc;
import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class OutputArc extends Arc<Transition, Place> {

    public OutputArc() {
        super();
    }

    public OutputArc(String id, ArcType type, Transition source, Place target) {
        super(id, type, source, target);
    }
}
