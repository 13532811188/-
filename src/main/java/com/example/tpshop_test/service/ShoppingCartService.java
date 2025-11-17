package com.example.tpshop_test.service;

import com.example.tpshop_test.entity.ShoppingCartVO;

import java.util.List;

public interface ShoppingCartService {

    // 加入购物车（已存在则累加数量）
    void addToCart(Long userId, Long productId, Integer quantity);

    // 获取用户购物车列表
    List<ShoppingCartVO> getUserCart(Long userId);

    // 更新购物车项数量
    void updateQuantity(Long userId, Long cartId, Integer quantity);

    // 删除购物车项
    void deleteCartItem(Long userId, Long cartId);
}