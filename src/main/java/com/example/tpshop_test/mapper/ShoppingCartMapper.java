package com.example.tpshop_test.mapper;

import com.example.tpshop_test.domain.ShoppingCart;
import com.example.tpshop_test.entity.ShoppingCartVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    // 新增购物车项（若已存在则更新数量）
    @Insert("INSERT INTO shopping_cart (user_id, product_id, quantity, create_time, update_time) " +
            "VALUES (#{userId}, #{productId}, #{quantity}, NOW(), NOW()) " +
            "ON DUPLICATE KEY UPDATE " + // 利用联合唯一索引，重复时更新数量
            "quantity = quantity + #{quantity}, " +
            "update_time = NOW()")
    int insertOrUpdate(ShoppingCart cart);

    // 查询用户的购物车列表（关联商品信息）
    @Select("SELECT sc.id, sc.user_id, sc.product_id, sc.quantity, sc.create_time, " +
            "p.name, p.price, p.image_url, p.stock " +
            "FROM shopping_cart sc " +
            "LEFT JOIN product p ON sc.product_id = p.id " +
            "WHERE sc.user_id = #{userId} " +
            "ORDER BY sc.create_time DESC")
    List<ShoppingCartVO> getUserCart(Long userId); // 用VO接收关联结果

    // 更新购物车项数量
    @Update("UPDATE shopping_cart SET quantity = #{quantity}, update_time = NOW() " +
            "WHERE id = #{id} AND user_id = #{userId}")
    int updateQuantity(ShoppingCart cart);

    // 删除购物车项
    @Delete("DELETE FROM shopping_cart WHERE id = #{id} AND user_id = #{userId}")
    int deleteCartItem(@Param("id") Long id, @Param("userId") Long userId);
}