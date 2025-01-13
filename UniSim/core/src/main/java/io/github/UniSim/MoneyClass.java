package io.github.UniSim;

public class MoneyClass {
    private int Money;

    //Initialises Money class
    public MoneyClass(int money){
        this.Money = money;
    }

    //Getter method
    public int getMoney(){
        return this.Money;
    }

    //Add money
    public void addMoney(int value){
        this.Money += value;
    }

    //Remove money
    public void remMoney(int value){
        this.Money -= value;
    }
}
