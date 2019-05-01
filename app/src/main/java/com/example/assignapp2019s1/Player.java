package com.example.assignapp2019s1;

public class Player {
    String hqAddress;
    int money;
    int income;

    public Player() {}
    public void setBase(String pos) {
         this.hqAddress = pos;
    }

    public void addMoney() {
        this.money+=income;
    }

    public void spendMoney(int amount) {
        this.money-=amount;
    }

    public void changeIncome(int amount) {
        this.income+= amount;
    }

    //makemove()

}
