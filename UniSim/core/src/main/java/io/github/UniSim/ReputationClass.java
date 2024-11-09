package io.github.UniSim;

public class ReputationClass {
    private int Rep;

    //Initialise Reputation class
    public ReputationClass(int rep){
        this.Rep = rep;
    }

    //Getter method
    public int getRep(){
        return this.Rep;
    }

    //Add reputation
    public void addRep(int value){
        this.Rep += value;
        if(this.Rep >= 100){
            this.Rep = 100;
        }
    }

    //Remove reputation
    public void remRep(int value){
        this.Rep -= value;
        if(this.Rep <= 0){
            this.Rep = 0;
        }
    }
}
