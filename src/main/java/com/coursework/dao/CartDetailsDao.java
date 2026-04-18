package com.coursework.dao;

import com.coursework.entity.CartDetails;
import java.util.ArrayList;

public interface CartDetailsDao {
    boolean addToCart(CartDetails cartDetails);
    ArrayList<CartDetails> getCartItems(int cartId);
    boolean updateQuantity(CartDetails cartDetails);
    boolean removeItem(int cartDetailId);
}