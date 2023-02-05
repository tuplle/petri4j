package dev.tuplle.petri4j.arc.input;

import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class WeightedInputArc extends InputArc{

    protected Long weight;

    public WeightedInputArc(){
        super();
        this.weight = 1L;
    }

    public WeightedInputArc(Long weight) {
        super();
        this.weight = weight;
    }

    public WeightedInputArc(String id, ArcType type, Place source, Transition target, Long weight) {
        super(id, type, source, target);
        this.weight = weight;
    }
}
