package com.example.assignapp2019s1;

public class Player {
    public String getHqAddress() {
        return hqAddress;
    }

    String hqAddress;

    public int getMoney() {
        return money;
    }

    int money;

    public void setMoney(int money) {
        this.money = money;
    }

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
