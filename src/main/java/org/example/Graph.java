package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {
    private ArrayList<State> states;

    public Graph(){
        states = new ArrayList<>();
    }

    public void addState(String name, float immediateReward){
        State state = new State(name, immediateReward);
        states.add(state);
    }

    public State getState(String name) throws Exception{
        for (int i = 0; i < states.size(); i++)
            if (name.equals(states.get(i).getName()))
                return states.get(i);

        for (int i = 0; i < states.size(); i++)
            System.out.println(states.get(i).getName());
        throw new Exception("State " + name + " not found");
    }

    public boolean addAction(String state, ArrayList<String> destinationNames, ArrayList<Double> probability) {
        for (int i = 0; i < states.size(); i++)
            if (states.get(i).getName().equals(state)) {
                try {
                    states.get(i).addAction(destinationNames, probability);
                    break;
                } catch (Exception e) {
                    return false;
                }

            }
        return true;
    }

    @Override
    public String toString() {
        String returnString = "Graph{\n";
        for (int i = 0; i < states.size(); i++){
            returnString = returnString + states.get(i).getName() + ":";
            for (int j = 0; j < states.get(i).getActions().size(); j++){
                returnString += "\n" + states.get(i).getActions().get(j).toString();
            }
            returnString += "\n";
        }

        returnString += "}";
        return returnString;
    }

    public void valueIteration(int numberOfIterations, double discount){
        Map<String, Double> current = new HashMap<>();
        Map<String, Double> old = new HashMap<>();

        //initializing
        for (int i = 0; i < states.size(); i++){
            current.put(states.get(i).getName(), 0.0);
            old.put(states.get(i).getName(), 0.0);
        }

        //iteration
        for (int i = 0; i < numberOfIterations; i++){ //for tot iterations
            for (int j = 0; j < states.size(); j++){ //for all the states
                double max = Double.NEGATIVE_INFINITY;
                for (int k = 0; k < states.get(j).getActions().size(); k++){ //for all actions
                    double value = 0;
                    Map<String, Double> currentAction = states.get(j).getActions().get(k).getTransitions();
                    Set keys = currentAction.keySet();
                    for (Object key : keys) {
                        String stringKey = key.toString();
                        value += currentAction.get(stringKey) * old.get(stringKey);
                    }
                    value *= discount;
                    value += states.get(j).getImmediateReward();

                    if (k == 0 || max < value)
                        max = value;


                }
                if (states.get(j).getActions().size() == 0) {
                    max = states.get(j).getImmediateReward();
                }

                current.replace(states.get(j).getName(), max);
            }

            for (int counter = 0; counter < states.size(); counter++)
                old.replace(states.get(counter).getName(), current.get(states.get(counter).getName()));
        }

        System.out.println("Iterations: " + numberOfIterations);
        System.out.println("Discount: " + discount);
        for (int i = 0; i < states.size(); i++){
            System.out.println(states.get(i).getName() + " -> " + old.get(states.get(i).getName()));
        }
    }

}
