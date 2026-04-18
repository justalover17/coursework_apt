package com.coursework.entity;

/**
 * CartDetails entity — represents items inside cart.
 * Maps to the `cart_details` table.
 */
public class CartDetails {

    private int cartDetailId;
    private int cartId;
    private int foodId;
    private int quantity;

    public CartDetails(int cartId, int foodId, int quantity) {
        this.cartId = cartId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public CartDetails(int cartDetailId, int cartId, int foodId, int quantity) {
        this.cartDetailId = cartDetailId;
        this.cartId = cartId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public int getCartDetailId() { return cartDetailId; }
    public int getCartId() { return cartId; }
    public int getFoodId() { return foodId; }
    public int getQuantity() { return quantity; }

    public void setCartDetailId(int cartDetailId) { this.cartDetailId = cartDetailId; }

    @Override
    public String toString() {
        return "CartItem: " + foodId + " Qty: " + quantity;
    }
}