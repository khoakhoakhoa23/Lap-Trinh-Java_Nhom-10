package uth.java.duan_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.entities.CartItem;
import uth.java.duan_java.repositories.CartItemRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cartitems")
public class CartItemController {

    @Autowired
    private CartItemRepo cartItemRepo;

    // 1. Lấy danh sách tất cả sản phẩm trong giỏ hàng theo cartId
    @GetMapping()
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepo.findAll();
        return cartItems.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(cartItems);
    }

    // 2. Thêm sản phẩm vào giỏ hàng
    @PostMapping("/{id}")
    public ResponseEntity<String> addCartItem(@RequestBody CartItem cartItem) {
        boolean exists = cartItemRepo.findByCartIdAndProductId(cartItem.getCart().getCartId(), cartItem.getProduct().getProductId()).isPresent();
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already exists in cart");
        }
        cartItemRepo.save(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cart item added successfully");
    }

    // 3. Cập nhật sản phẩm trong giỏ hàng
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
        return cartItemRepo.findById(id)
                .map(cartItem -> {
                    cartItem.setQuantity(updatedCartItem.getQuantity());
                    cartItemRepo.save(cartItem);
                    return ResponseEntity.ok("Cart item updated successfully");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found"));
    }

    // 4. Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long id) {
        return cartItemRepo.findById(id)
                .map(cartItem -> {
                    cartItemRepo.delete(cartItem);
                    return ResponseEntity.ok("Cart item removed successfully");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found"));
    }
}
