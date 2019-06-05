package com.company.entities.storage;

public class Goods {
    private int amount;
    private String title;

    public Goods(int amount, String title){
        this.amount = amount;
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "amount=" + amount +
                '}';
    }
}
