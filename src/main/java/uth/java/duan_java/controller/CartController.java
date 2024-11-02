package uth.java.duan_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.dtos.CartDTO;
import uth.java.duan_java.models.entities.Cart;
import uth.java.duan_java.models.entities.CartItem;
import uth.java.duan_java.models.entities.Product;
import uth.java.duan_java.repositories.CartItemRepo;
import uth.java.duan_java.repositories.CartRepo;
import uth.java.duan_java.repositories.ProductRepo;
import uth.java.duan_java.services.CartService;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartService cartService;

    // 1. Xem giỏ hàng của người dùng
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartItems(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartById(userId);
        return ResponseEntity.ok(cart);
    }

    // 2. Thêm sản phẩm vào giỏ hàng
    @PostMapping("/{userId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        Optional<Cart> cart = cartRepo.findByUserId(userId);
        Optional<Product> product = productRepo.findById(productId);

        if (cart.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found for user");
        }

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        // Kiểm tra xem giỏ hàng có chứa sản phẩm không
        if (cartService.doesCartContainProduct(productId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already exists in cart");
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart.get());
        cartItem.setProduct(product.get());
        cartItem.setQuantity(quantity);
        cartItemRepo.save(cartItem);

        return ResponseEntity.ok("Product added to cart");
    }


    // 3. Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{userId}/{productId}")
    public ResponseEntity<String> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam int quantity) {
        Optional<Cart> cart = cartRepo.findByUserId(userId);
        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<CartItem> cartItem = cartItemRepo.findByCartIdAndProductId(cart.get().getCartId(), productId);
        if (cartItem.isEmpty()) {
            return new ResponseEntity<>("Product not found in cart", HttpStatus.NOT_FOUND);
        }

        CartItem existingCartItem = cartItem.get();
        existingCartItem.setQuantity(quantity);
        cartItemRepo.save(existingCartItem);
        return new ResponseEntity<>("Cart item updated", HttpStatus.OK);
    }


    // 4. Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long userId, @RequestParam Long productId) {
        Optional<Cart> cart = cartRepo.findByUserId(userId);
        if (cart.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found for user");
        }

        Optional<CartItem> cartItem = cartItemRepo.findByCartIdAndProductId(cart.get().getCartId(), productId);
        if (cartItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart");
        }

        cartItemRepo.delete(cartItem.get());
        return ResponseEntity.ok("Product removed from cart");
    }
}
