package com.example.silicon.billassignment;

public class Calculate {
    private float totalAmount;
    private float tipAmount;
    private float shareAmount;
    private int totalPersons;

    public Calculate(){
        this.totalAmount = 0;
        this.tipAmount = 0;
        this.shareAmount = 0;
        this.totalPersons = 1;
    }

    public Calculate(float totalAmount,float tipAmount,int totalPersons){
        this.tipAmount = tipAmount;
        this.totalAmount = totalAmount;
        this.totalPersons = totalPersons;
    }

    public float calculateShareAmount(){
        return (getTipAmount() + getTotalAmount())/getTotalPersons();
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTipAmount(float tipAmount) {
        this.tipAmount = tipAmount;
    }

    public float getTipAmount() {
        return tipAmount;
    }

    public void setTotalPersons(int totalPersons) {
        this.totalPersons = totalPersons;
    }

    public int getTotalPersons() {
        return totalPersons;
    }
}
