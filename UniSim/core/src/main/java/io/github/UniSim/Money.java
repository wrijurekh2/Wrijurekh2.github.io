package io.github.UniSim;

public class Money {
    private double Money;

    //Initialises Money class
    public Money(double money){
        this.Money = money;
    }

    //Getter method
    public double getMoney(){
        return this.Money;
    }

    //Add money
    public void addMoney(double value){
        this.Money += value;
    }

    //Remove money
    public void remMoney(double value){
        this.Money -= value;
    }
}
