package com.mladoniczky.petri4j.arc.input;

import com.mladoniczky.petri4j.arc.ArcType;
import com.mladoniczky.petri4j.place.Place;
import com.mladoniczky.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResetArc extends InputArc {

    public ResetArc() {
        super();
        this.type = ArcType.RESET;
    }

    public ResetArc(String id, Place source, Transition target) {
        super(id, ArcType.RESET, source, target);
    }

    @Override
    public void moveResources() {
        // do nothing
    }

    @Override
    public boolean test() {
        return true;
    }
}
