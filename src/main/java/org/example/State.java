package org.example;
import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private double immediateReward;
    private List<Action> actions;

    public State(String name, double immediateReward){
        this.name = name;
        this.immediateReward = immediateReward;
        actions = new ArrayList<Action>();
    }

    public String getName(){
        return name;
    }

    public double getImmediateReward(){
        return immediateReward;
    }

    public List<Action> getActions(){
        return actions;
    }

    public void addAction(ArrayList<String> destinationNames, ArrayList<Double> probability) throws Exception {
        if (destinationNames.size() != probability.size())
            throw new Exception("Lists size doesn't match");

        float sum = 0;
        for(int i = 0; i < probability.size(); i++){
            if (probability.get(i) < 0)
                throw new Exception("Invalid transition function");
            sum += probability.get(i);
        }
        if (sum < 0.98 || sum > 1.02)
            throw new Exception("Sum of probabilities is wrong bruh");

        Action action = new Action();
        for (int i = 0; i < destinationNames.size(); i++){
            action.addTransition(destinationNames.get(i), probability.get(i));
        }
        actions.add(action);
    }
}
