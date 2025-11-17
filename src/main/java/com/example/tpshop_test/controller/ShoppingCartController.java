package com.example.tpshop_test.controller;

import com.example.tpshop_test.entity.Result;
import com.example.tpshop_test.entity.ShoppingCartVO;
import com.example.tpshop_test.service.ShoppingCartService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService cartService;

    // 1. 加入购物车
    @PostMapping("/add")
    public Result<Void> addToCart(
            HttpServletRequest request,
            @RequestParam Long productId,
            @RequestParam Integer quantity
    ) {
        try {
            Long userId = (Long) request.getAttribute("userId"); // 从请求属性获取用户ID
            cartService.addToCart(userId, productId, quantity);
            return Result.success(null, "加入购物车成功");
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage());
        } catch (RuntimeException e) {
            return Result.error("400", e.getMessage());
        }
    }

    // 2. 获取用户购物车列表
    @GetMapping("/list")
    public Result<List<ShoppingCartVO>> getUserCart(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            List<ShoppingCartVO> cartList = cartService.getUserCart(userId);
            return Result.success(cartList, "购物车列表查询成功");
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage());
        }
    }

    // 3. 更新购物车项数量
    @PutMapping("/update")
    public Result<Void> updateQuantity(
            HttpServletRequest request,
            @RequestParam Long cartId,
            @RequestParam Integer quantity
    ) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            cartService.updateQuantity(userId, cartId, quantity);
            return Result.success(null, "购物车数量更新成功");
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage());
        } catch (RuntimeException e) {
            return Result.error("400", e.getMessage());
        }
    }

    // 4. 删除购物车项
    @DeleteMapping("/delete")
    public Result<Void> deleteCartItem(
            HttpServletRequest request,
            @RequestParam Long cartId
    ) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            cartService.deleteCartItem(userId, cartId);
            return Result.success(null, "购物车项删除成功");
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage());
        } catch (RuntimeException e) {
            return Result.error("400", e.getMessage());
        }
    }
}