package com.example.tpshop_test.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 购物车实体类
 */
@Data
public class ShoppingCart {
    private Long id; // 购物车项ID（主键）
    private Long userId; // 用户ID（关联用户表）
    private Long productId; // 商品ID（关联商品表）
    private Integer quantity; // 购买数量
    private LocalDateTime createTime; // 加入时间
    private LocalDateTime updateTime; // 更新时间（数量变更时）
}