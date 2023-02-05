package dev.tuplle.petri4j.arc.input;

import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InhibitorArc extends WeightedInputArc {

    public InhibitorArc() {
        super();
        this.type = ArcType.INHIBITOR;
    }

    public InhibitorArc(Long weight) {
        super(weight);
        this.type = ArcType.INHIBITOR;
    }

    public InhibitorArc(String id, Place source, Transition target, Long weight) {
        super(id, ArcType.INHIBITOR, source, target, weight);
    }

    @Override
    public boolean test() {
        return this.source.getResources() < weight;
    }

    @Override
    public void moveResources() {
        // do nothing
    }
}
