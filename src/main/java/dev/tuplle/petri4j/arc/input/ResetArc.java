package dev.tuplle.petri4j.arc.input;

import dev.tuplle.petri4j.arc.ArcType;
import dev.tuplle.petri4j.place.Place;
import dev.tuplle.petri4j.transition.Transition;
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
        this.source.decrease(this.source.getResources());
    }

    @Override
    public boolean test() {
        return true;
    }
}
