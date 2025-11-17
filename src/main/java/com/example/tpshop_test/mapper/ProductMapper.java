package com.example.tpshop_test.mapper;

import com.example.tpshop_test.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品数据访问层
 */
@Mapper
public interface ProductMapper {

    // 查询所有商品（按创建时间倒序）
    @Select("SELECT * FROM product ORDER BY create_time DESC")
    List<Product> findAll();

    // 根据ID查询商品详情
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(Long id);

    // 根据分类查询商品（按价格升序）
    @Select("SELECT * FROM product WHERE category = #{category} ORDER BY price ASC")
    List<Product> findByCategory(String category);

    // 模糊搜索商品（根据名称）
    @Select("SELECT * FROM product WHERE name LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchByName(String keyword);

    // 查询热门商品（按销量降序取前10）
    @Select("SELECT * FROM product ORDER BY sales DESC LIMIT 10")
    List<Product> findHotProducts();
}