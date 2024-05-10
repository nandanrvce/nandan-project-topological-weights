package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testCase1() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.25));
        rows.add(new DBRow("B", "C", 0.5));
//        rows.add(new DBRow("C", "D", 0.5));
//        rows.add(new DBRow("C", "D", 0.5));
//        rows.add(new DBRow("C", "E", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase2() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.5));
        rows.add(new DBRow("C", "D", 0.5));
        rows.add(new DBRow("B", "C", 0.5));
        rows.add(new DBRow("B", "D", 0.5));
        rows.add(new DBRow("C", "E", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase3() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("B", "C", 0.5));
        rows.add(new DBRow("B", "D", 0.5));
        rows.add(new DBRow("C", "D", 0.5));
        rows.add(new DBRow("C", "E", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase4() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.5));
        rows.add(new DBRow("B", "C", 0.5));
        rows.add(new DBRow("A", "C", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase5() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.5));
        rows.add(new DBRow("B", "C", 0.5));
        rows.add(new DBRow("A", "C", 0.5));


        rows.add(new DBRow("P", "Q", 0.25));
        rows.add(new DBRow("Q", "R", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase6() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.5));
        rows.add(new DBRow("A", "C", 0.5));
        rows.add(new DBRow("B", "C", 0.5));
        rows.add(new DBRow("B", "D", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase7() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.5));
        rows.add(new DBRow("A", "C", 0.5));
        rows.add(new DBRow("B", "C", 0.25));
        rows.add(new DBRow("B", "D", 0.25));
        rows.add(new DBRow("M", "N", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase8() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.25));
        rows.add(new DBRow("B", "C", 0.5));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }

    @Test
    void testCase9() {
        final List<DBRow> rows = new ArrayList<>();
        rows.add(new DBRow("A", "B", 0.25));

        Graph graph = new Graph();
        graph.prepare(rows);
        System.out.println("Input");
        graph.print();
        graph.simplify();
        System.out.println("Output");
        graph.print();
    }
}