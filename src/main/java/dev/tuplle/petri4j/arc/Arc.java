package dev.tuplle.petri4j.arc;

import dev.tuplle.petri4j.generic.Node;
import lombok.Data;

@Data
public abstract class Arc<T extends Node, R extends Node> {

    protected String id;
    protected ArcType type;
    protected T source;
    protected R target;

    public Arc() {
    }

    public Arc(String id, ArcType type, T source, R target) {
        this();
        this.id = id;
        this.type = type;
        this.source = source;
        this.target = target;
    }

    public abstract void moveResources();
}

