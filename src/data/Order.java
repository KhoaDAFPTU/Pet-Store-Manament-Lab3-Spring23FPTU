/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

public class Order implements Serializable{
    private int id;
    private String date;
    private String nameCustomer;
    private int countPet;
    private double total;

    public Order(int id, String date, String nameCustomer, int countPet, double total) {
        this.id = id;
        this.date = date;
        this.nameCustomer = nameCustomer;
        this.countPet = countPet;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public int getCountPet() {
        return countPet;
    }

    public void setCountPet(int countPet) {
        this.countPet = countPet;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
