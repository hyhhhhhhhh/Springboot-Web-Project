package com.eplatform.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableAutoConfiguration
public class Commodity {


    private int id;
    private String ProductName;
    private int Quantity;
    private float Price;
    private int Sales;
    private int ViewTimes;
    private float Discount;
    private String Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getSales() {
        return Sales;
    }

    public void setSales(int sales) {
        Sales = sales;
    }

    public int getViewTimes() {
        return ViewTimes;
    }

    public void setViewTimes(int viewTimes) {
        ViewTimes = viewTimes;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Commodity(Map<String, Object> maps) {
        id= (int) maps.get("id");
        ProductName= String.valueOf(maps.get("ProductName"));
        Quantity= (int) maps.get("Quantity");
        Price = (float) maps.get("Price");
        Sales= (int) maps.get("Sales");
        ViewTimes= (int) maps.get("ViewTimes");
        Discount= (float)maps.get("Discount");
        Date = String.valueOf(maps.get("Date"));
    }
}
