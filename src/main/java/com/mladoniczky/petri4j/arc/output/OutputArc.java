package com.mladoniczky.petri4j.arc.output;

import com.mladoniczky.petri4j.arc.Arc;
import com.mladoniczky.petri4j.arc.ArcType;
import com.mladoniczky.petri4j.place.Place;
import com.mladoniczky.petri4j.transition.Transition;
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
