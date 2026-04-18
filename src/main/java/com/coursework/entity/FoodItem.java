package com.coursework.entity;

import java.sql.Timestamp;

public class FoodItem {

    private int foodId;
    private String name;
    private double price;
    private int categoryId;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public FoodItem(String name, double price, int categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public FoodItem(int foodId, String name, double price, int categoryId, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getFoodId() { return foodId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public String getDescription() { return description; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setFoodId(int foodId) { this.foodId = foodId; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }  // ← ADD THIS METHOD
}