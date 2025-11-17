package com.example.tpshop_test.service.serviceImpl;

import com.example.tpshop_test.domain.Product;
import com.example.tpshop_test.mapper.ProductMapper;
import com.example.tpshop_test.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        // 校验ID合法性
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("商品ID必须为正整数");
        }
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new RuntimeException("该商品不存在");
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        // 校验分类参数
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("分类不能为空");
        }
        return productMapper.findByCategory(category);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        // 关键词为空时返回空列表
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        return productMapper.searchByName(keyword.trim());
    }

    @Override
    public List<Product> getHotProducts() {
        return productMapper.findHotProducts();
    }
}