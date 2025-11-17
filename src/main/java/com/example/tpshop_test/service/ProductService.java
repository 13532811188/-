package com.example.tpshop_test.service;

import com.example.tpshop_test.domain.Product;

import java.util.List;

/**
 * 商品服务接口
 */
public interface ProductService {

    // 获取所有商品
    List<Product> getAllProducts();

    // 根据ID获取商品详情
    Product getProductById(Long id);

    // 根据分类筛选商品
    List<Product> getProductsByCategory(String category);

    // 搜索商品（按名称模糊匹配）
    List<Product> searchProducts(String keyword);

    // 获取热门商品（销量前10）
    List<Product> getHotProducts();
}