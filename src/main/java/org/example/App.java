package org.example;

import java.util.ArrayList;

public class App {
    public static void main (String args[]){
        System.out.println("yohoho");

        Graph graph = new Graph();
        graph.addState("s0", 1);
        ArrayList<String> bruh = new ArrayList<>();
        ArrayList<Double> brah = new ArrayList<>();
        bruh.add("s1"); bruh.add("s2");
        brah.add(0.5); brah.add(0.5);
        graph.addAction("s0", bruh, brah);

        graph.addState("s1", 2);
        ArrayList<String> bruh1 = new ArrayList<>();
        ArrayList<Double> brah1 = new ArrayList<>();
        bruh1.add("s1"); bruh1.add("s3");
        brah1.add(0.5); brah1.add(0.5);
        graph.addAction("s1", bruh1, brah1);
        ArrayList<String> bruh2 = new ArrayList<>(); bruh2.add("s2");
        ArrayList<Double> brah2 = new ArrayList<>(); brah2.add(1.0);
        graph.addAction("s1", bruh2, brah2);

        graph.addState("s2", 3);
        ArrayList<String> bruh3 = new ArrayList<>(); bruh3.add("s0");
        ArrayList<Double> brah3 = new ArrayList<>(); brah3.add(1.0);
        graph.addAction("s2", bruh3, brah3);

        graph.addState("s3", 10);
        ArrayList<String> bruh4 = new ArrayList<>(); bruh4.add("s3");
        ArrayList<Double> brah4 = new ArrayList<>(); brah4.add(1.0);
        graph.addAction("s3", bruh4, brah4);

        System.out.println(graph);

        try {
            System.out.println("s0, " + graph.getState("s0").getActions().size());
            System.out.println("s1, " + graph.getState("s1").getActions().size());
            System.out.println("s2, " + graph.getState("s2").getActions().size());
            System.out.println("s3, " + graph.getState("s3").getActions().size());
        }
        catch (Exception e){
            System.out.println(e);
        }

        graph.valueIteration(3000, 0.9);


        Graph graph2 = new Graph();
        graph2.addState("s1", 0);
        graph2.addState("s2", 0);
        graph2.addState("s3", 1);
        graph2.addState("s4", 0);

        ArrayList<String> next = new ArrayList<>();
        ArrayList<Double> prob = new ArrayList<>();
        next.add("s1"); next.add("s2");
        prob.add(0.2); prob.add(0.8);
        graph2.addAction("s1", next, prob);

        next.clear(); prob.clear();
        next.add("s1"); next.add("s4");
        prob.add(0.2); prob.add(0.8);
        graph2.addAction("s1", next, prob);

        next.clear(); prob.clear();
        next.add("s2"); next.add("s1");
        prob.add(0.2); prob.add(0.8);
        graph2.addAction("s2", next, prob);

        next.clear(); prob.clear();
        next.add("s2"); next.add("s3");
        prob.add(0.2); prob.add(0.8);
        graph2.addAction("s2", next, prob);

        next.clear(); prob.clear();
        next.add("s4"); next.add("s1");
        prob.add(0.2); prob.add(0.8);
        graph2.addAction("s4", next, prob);

        next.clear(); prob.clear();
        next.add("s4"); next.add("s3");
        prob.add(0.1); prob.add(0.9);
        graph2.addAction("s4", next, prob);

        next.clear(); prob.clear();
        next.add("s4"); prob.add(1.0);
        graph2.addAction("s3", next, prob);

        next.clear(); prob.clear();
        next.add("s2"); prob.add(1.0);
        graph2.addAction("s3", next, prob);

        graph2.valueIteration(100, 0.5);

    }
}
