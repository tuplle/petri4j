package dev.tuplle.petri4j.arc.input;

import dev.tuplle.petri4j.arc.Arc;
import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class InputArc extends Arc<Place, Transition> {

    public InputArc() {
    }

    public InputArc(String id, ArcType type, Place source, Transition target) {
        super(id, type, source, target);
    }

    public abstract boolean test();
}
