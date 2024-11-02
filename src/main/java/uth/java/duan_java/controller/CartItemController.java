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
@RequestMapping("/api/cart/items")
public class CartItemController {

    @Autowired
    private CartItemRepo cartItemRepo;

    // 1. Lấy danh sách tất cả sản phẩm trong giỏ hàng theo cartId
    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItem>> getAllCartItems(@PathVariable Long cartId) {
        List<CartItem> cartItems = cartItemRepo.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    // 2. Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public ResponseEntity<String> addCartItem(@RequestBody CartItem cartItem) {
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Optional<CartItem> existingCartItem = cartItemRepo.findByCartIdAndProductId(cartItem.getCart().getCartId(), cartItem.getProduct().getId());
        if (existingCartItem.isPresent()) {
            return new ResponseEntity<>("Product already exists in cart", HttpStatus.CONFLICT);
        }

        cartItemRepo.save(cartItem);
        return new ResponseEntity<>("Cart item added successfully", HttpStatus.CREATED);
    }

    // 3. Cập nhật sản phẩm trong giỏ hàng
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long id, @RequestBody CartItem updatedCartItem) {
        Optional<CartItem> existingCartItem = cartItemRepo.findById(id);
        if (existingCartItem.isEmpty()) {
            return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
        }

        CartItem cartItem = existingCartItem.get();
        cartItem.setQuantity(updatedCartItem.getQuantity()); // Cập nhật số lượng
        cartItemRepo.save(cartItem);
        return new ResponseEntity<>("Cart item updated successfully", HttpStatus.OK);
    }

    // 4. Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long id) {
        Optional<CartItem> existingCartItem = cartItemRepo.findById(id);
        if (existingCartItem.isEmpty()) {
            return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
        }

        cartItemRepo.delete(existingCartItem.get());
        return new ResponseEntity<>("Cart item removed successfully", HttpStatus.OK);
    }
}
