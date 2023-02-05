package dev.tuplle.petri4j.arc.input;

import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularInputArc extends WeightedInputArc {

    public RegularInputArc() {
        super();
        this.type = ArcType.REGULAR;
    }

    public RegularInputArc(Long weight) {
        super(weight);
        this.type = ArcType.REGULAR;
    }

    public RegularInputArc(String id, Place source, Transition target, Long weight) {
        super(id, ArcType.REGULAR, source, target, weight);
    }

    @Override
    public boolean test() {
        return this.source.getResources() >= weight;
    }

    @Override
    public void moveResources() {
        this.source.decrease(weight);
    }
}
