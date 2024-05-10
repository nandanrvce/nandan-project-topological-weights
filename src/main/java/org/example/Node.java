package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Node {
    String val;
    List<Edge> edges;

    @Override
    public String toString() {
        return val;
    }
}
