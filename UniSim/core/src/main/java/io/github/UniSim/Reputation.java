package io.github.UniSim;

public class Reputation {
    private int Rep;

    public Reputation(int rep){
        this.Rep = rep;
    }

    public double getRep(){
        return this.Rep;
    }

    public void addRep(int value){
        this.Rep += value;
    }

    public void remRep(int value){
        this.Rep -= value;
    }
}
