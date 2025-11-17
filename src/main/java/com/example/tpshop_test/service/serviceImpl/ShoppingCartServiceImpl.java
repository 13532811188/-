package com.example.tpshop_test.service.serviceImpl;

import com.example.tpshop_test.domain.Product;
import com.example.tpshop_test.domain.ShoppingCart;
import com.example.tpshop_test.entity.ShoppingCartVO;
import com.example.tpshop_test.mapper.ShoppingCartMapper;
import com.example.tpshop_test.service.ProductService;
import com.example.tpshop_test.service.ShoppingCartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartMapper cartMapper;

    @Resource
    private ProductService productService; // 依赖商品服务检查商品状态

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        // 1. 参数校验
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("用户ID无效");
        }
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("商品ID无效");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("购买数量必须大于0");
        }

        // 2. 检查商品是否存在及库存是否充足
        Product product = productService.getProductById(productId);
        if (product.getStock() < quantity) {
            throw new RuntimeException("商品库存不足，当前库存：" + product.getStock());
        }

        // 3. 加入购物车（已存在则累加数量）
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cartMapper.insertOrUpdate(cart);
    }

    @Override
    public List<ShoppingCartVO> getUserCart(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("用户ID无效");
        }
        return cartMapper.getUserCart(userId);
    }

    @Override
    public void updateQuantity(Long userId, Long cartId, Integer quantity) {
        // 校验参数
        if (cartId == null || cartId <= 0) {
            throw new IllegalArgumentException("购物车项ID无效");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("购买数量必须大于0");
        }

        // 检查数量是否超过商品库存（先获取商品ID）
        ShoppingCartVO cartVO = cartMapper.getUserCart(userId).stream()
                .filter(vo -> vo.getId().equals(cartId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("购物车项不存在"));
        if (quantity > cartVO.getStock()) {
            throw new RuntimeException("超过商品最大库存，当前库存：" + cartVO.getStock());
        }

        // 更新数量
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        cart.setUserId(userId);
        cart.setQuantity(quantity);
        cartMapper.updateQuantity(cart);
    }

    @Override
    public void deleteCartItem(Long userId, Long cartId) {
        if (cartId == null || cartId <= 0) {
            throw new IllegalArgumentException("购物车项ID无效");
        }
        int rows = cartMapper.deleteCartItem(cartId, userId);
        if (rows == 0) {
            throw new RuntimeException("购物车项不存在或已删除");
        }
    }
}