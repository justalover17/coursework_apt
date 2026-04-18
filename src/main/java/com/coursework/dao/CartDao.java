package com.coursework.dao;

import com.coursework.entity.Cart;
import java.util.ArrayList;

public interface CartDao {
    boolean createCart(Cart cart);
    Cart getCartByUserId(int userId);
    boolean deleteCart(int cartId);
}