package io.github.UniSim;

public class Money {
    private double Money;

    public Money(double money){
        this.Money = money;
    }

    public double getMoney(){
        return this.Money;
    }

    public void addMoney(double value){
        this.Money += value;
    }

    public void remMoney(double value){
        this.Money -= value;
    }
}
