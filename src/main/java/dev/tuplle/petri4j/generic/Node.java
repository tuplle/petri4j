package dev.tuplle.petri4j.generic;

import lombok.Data;

@Data
public abstract class Node {

    protected String id;
    protected String name;

    public Node() {
    }

    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
