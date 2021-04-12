package com.mladoniczky.petri4j.arc.input;

import com.mladoniczky.petri4j.arc.Arc;
import com.mladoniczky.petri4j.arc.ArcType;
import com.mladoniczky.petri4j.place.Place;
import com.mladoniczky.petri4j.transition.Transition;
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
