package dev.tuplle.petri4j.arc.input;

import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReadArc extends WeightedInputArc{

    public ReadArc() {
        super();
        this.type = ArcType.READ;
    }

    public ReadArc(Long weight) {
        super(weight);
        this.type = ArcType.READ;
    }

    public ReadArc(String id, Place source, Transition target, Long weight) {
        super(id, ArcType.READ, source, target, weight);
    }

    @Override
    public void moveResources() {
        // do nothing
    }

    @Override
    public boolean test() {
        return this.source.getResources() >= weight;
    }
}
