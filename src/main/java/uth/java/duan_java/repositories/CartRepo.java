package uth.java.duan_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uth.java.duan_java.models.entities.Cart;

import java.util.Optional;


public interface CartRepo extends JpaRepository<Cart, Long> {


    // Tìm giỏ hàng chứa sản phẩm với productId
    @Query("SELECT c FROM Cart c JOIN c.cartItems ci WHERE ci.product.productId = :productId")
    Optional<Cart> findByProductId(@Param("productId") Long productId);


    // Tìm giỏ hàng theo userId
    @Query("SELECT c FROM Cart c WHERE c.user.userId=:userId")
    Optional<Cart> findByUserId(@Param("userId") Long userId);
}
