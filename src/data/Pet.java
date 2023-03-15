/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;

public class Pet implements Serializable{
    private String id;
    private String description;
    private String date;
    private double price;
    private String category;

    public Pet(String id, String description, String date, double price, String category) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Pet{" + "id=" + id + ", description=" + description + ", date=" + date + ", price=" + price + ", category=" + category + '}';
    }
    
    public void showInfor(){
        System.out.printf("|%-5s|%-20s|%-15s|%-10.1f|%-10s|\n",id,description,date,price,category);
    }
}
