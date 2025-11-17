package com.example.tpshop_test.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车视图对象（包含商品详情）
 */
@Data
public class ShoppingCartVO {
    private Long id; // 购物车项ID
    private Long userId; // 用户ID
    private Long productId; // 商品ID
    private Integer quantity; // 购买数量
    private LocalDateTime createTime; // 加入时间

    // 商品关联信息
    private String name; // 商品名称
    private BigDecimal price; // 商品单价
    private String imageUrl; // 商品图片
    private Integer stock; // 商品库存（用于前端判断是否可购买）
}