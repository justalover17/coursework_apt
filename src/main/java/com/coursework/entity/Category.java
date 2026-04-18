package com.coursework.entity;

import java.sql.Timestamp;

/**
 * Category entity — represents food categories.
 * Maps to the `category` table.
 */
public class Category {

    private int categoryId;
    private String categoryName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int categoryId, String categoryName, Timestamp createdAt, Timestamp updatedAt) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    @Override
    public String toString() {
        return "[" + categoryId + "] " + categoryName;
    }
}