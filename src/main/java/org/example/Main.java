package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.25));
        rows.add(new DBRow("B", "C", 0.5));
//        rows.add(new DBRow("C", "D", 0.5));
//        rows.add(new DBRow("C", "D", 0.5));
//        rows.add(new DBRow("C", "E", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        graph.simplify();
        graph.print();

    }
}