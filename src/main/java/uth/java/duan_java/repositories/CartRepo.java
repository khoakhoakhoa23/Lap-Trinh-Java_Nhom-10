package uth.java.duan_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uth.java.duan_java.models.entities.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {

    // Tìm giỏ hàng chứa sản phẩm với productId (nhiều giỏ hàng có thể chứa cùng một sản phẩm)
    @Query("SELECT c FROM Cart c JOIN c.cartItems ci WHERE ci.product.productId = :productId")
    List<Cart> findCartsByProductId(@Param("productId") Long productId);

    // Tìm giỏ hàng theo userId
    @Query("SELECT c FROM Cart c WHERE c.user.userId = :userId")
    Optional<Cart> findByUserId(@Param("userId") Long userId);

    // Kiểm tra xem giỏ hàng có chứa sản phẩm nhất định dựa trên cartId và productId
    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.product.productId = :productId")
    boolean existsByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);
}
