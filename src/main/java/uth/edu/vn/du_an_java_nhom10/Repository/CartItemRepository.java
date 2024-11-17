package uth.edu.vn.du_an_java_nhom10.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uth.edu.vn.du_an_java_nhom10.Model.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Tìm các CartItem trong giỏ hàng theo cartId
    List<CartItem> findByCartId(Long cartId);

    // Tìm CartItem theo cartId và productId
    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    List<CartItem> findByUserId(Long userId);
}
