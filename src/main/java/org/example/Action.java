package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Action {
    private Map<String, Double> transitions;
    private static int counter = 0;

    public Action(){
        transitions = new HashMap<>();
    }

    public void addTransition(String destinationName, Double probability){
        transitions.put(destinationName, probability);
        //System.out.println(destinationName + "->" + probability);
    }

    public String toString(){
        String string = "\t";

        Set keys = transitions.keySet();
        for (Object key : keys){
            String stringKey = key.toString();
            string += stringKey + "->" + transitions.get(stringKey) + "\t";

        }

        return string;
    }

    public Map<String, Double> getTransitions(){
        return transitions;
    }
}
