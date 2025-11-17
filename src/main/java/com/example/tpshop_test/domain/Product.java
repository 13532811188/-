package com.example.tpshop_test.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体类
 */
@Data // Lombok注解，自动生成getter、setter等方法
public class Product {
    private Long id; // 商品ID（主键）
    private String name; // 商品名称
    private String description; // 商品描述
    private BigDecimal price; // 商品价格（用BigDecimal避免精度问题）
    private Integer stock; // 库存数量
    private String category; // 商品分类（如"家电"、"服饰"）
    private String imageUrl; // 商品图片URL
    private Integer sales; // 销量
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}