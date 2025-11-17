package com.example.tpshop_test.controller;

import com.example.tpshop_test.domain.Product;
import com.example.tpshop_test.entity.Result;
import com.example.tpshop_test.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品控制器：处理商品浏览相关请求
 */
@RestController
@RequestMapping("/api/products") // 接口统一前缀
public class ProductController {

    @Resource
    private ProductService productService;

    // 1. 获取所有商品
    @GetMapping
    public Result<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return Result.success(products, "商品列表查询成功");
    }

    // 2. 获取商品详情（通过路径参数传递ID）
    @GetMapping("/{id}")
    public Result<Product> getProductDetail(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return Result.success(product, "商品详情查询成功");
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage()); // 参数错误
        } catch (RuntimeException e) {
            return Result.error("404", e.getMessage()); // 商品不存在
        }
    }

    // 3. 按分类筛选商品（通过请求参数传递分类）
    @GetMapping("/category")
    public Result<List<Product>> getByCategory(@RequestParam String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            return Result.success(products, "分类商品查询成功");
        } catch (IllegalArgumentException e) {
            return Result.error("400", e.getMessage());
        }
    }

    // 4. 搜索商品（通过请求参数传递关键词）
    @GetMapping("/search")
    public Result<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return Result.success(products, "商品搜索成功");
    }

    // 5. 获取热门商品
    @GetMapping("/hot")
    public Result<List<Product>> getHotProducts() {
        List<Product> hotProducts = productService.getHotProducts();
        return Result.success(hotProducts, "热门商品查询成功");
    }
}