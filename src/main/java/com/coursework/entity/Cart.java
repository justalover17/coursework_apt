package com.coursework.entity;

import java.sql.Timestamp;

/**
 * Cart entity — represents user's cart.
 * Maps to the `cart` table.
 */
public class Cart {

    private int cartId;
    private int userId;
    private Timestamp createdAt;

    public Cart(int userId) {
        this.userId = userId;
    }

    public Cart(int cartId, int userId, Timestamp createdAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public int getCartId() { return cartId; }
    public int getUserId() { return userId; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setCartId(int cartId) { this.cartId = cartId; }

    @Override
    public String toString() {
        return "Cart ID: " + cartId + " User ID: " + userId;
    }
}