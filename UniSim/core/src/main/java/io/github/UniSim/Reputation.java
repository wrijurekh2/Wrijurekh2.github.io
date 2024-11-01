package io.github.UniSim;

public class Reputation {
    private int Rep;

    //Initialise Reputation class
    public Reputation(int rep){
        this.Rep = rep;
    }

    //Getter method
    public double getRep(){
        return this.Rep;
    }

    //Add reputation
    public void addRep(int value){
        this.Rep += value;
    }

    //Remove reputation
    public void remRep(int value){
        this.Rep -= value;
    }
}
