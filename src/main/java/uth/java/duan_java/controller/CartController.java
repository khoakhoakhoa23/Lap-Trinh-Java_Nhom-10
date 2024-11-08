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
    import uth.java.duan_java.services.CartService;
    import java.util.List;
    import java.util.Optional;
    import java.util.HashMap;
    import java.util.Map;

    @RestController
    @RequestMapping("/cart")
    public class CartController {

        @Autowired
        private CartRepo cartRepo;

        @Autowired
        private CartItemRepo cartItemRepo;

        @Autowired
        private CartService cartService;

        // Lấy tất cả giỏ hàng
        @GetMapping
        public ResponseEntity<List<CartDTO>> getAllCarts() {
            List<CartDTO> carts = cartService.getAllCarts();
            return ResponseEntity.ok(carts);
        }

        // Xem giỏ hàng của người dùng
        @GetMapping("/{userId}")
        public ResponseEntity<Map<String, Object>> getCartItems(@PathVariable Long userId) {
            Map<String, Object> response = new HashMap<>();

            try {
                CartDTO cartDTO = cartService.getCartById(userId);
                if (cartDTO != null) {
                    response.put("cart", cartDTO); // Trả về giỏ hàng nếu tìm thấy
                    return ResponseEntity.ok(response);
                } else {
                    // Nếu cartDTO là null, trả về lỗi 404
                    response.put("error", "Cart not found for user");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            } catch (RuntimeException e) {
                // Nếu có lỗi khác xảy ra, trả về thông điệp lỗi
                response.put("error", "An error occurred while fetching the cart: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }




        // Thêm sản phẩm vào giỏ hàng
        @PostMapping("/{userId}")
        public ResponseEntity<Map<String, String>> addProductToCart(
                @PathVariable Long userId,
                @RequestBody Map<String, Object> request) {

            // Kiểm tra xem "productId" có tồn tại và không null
            if (!request.containsKey("productId") || request.get("productId") == null) {
                return createErrorResponse("Product ID is required", HttpStatus.BAD_REQUEST);
            }

            Long productId = Long.valueOf(request.get("productId").toString());

            // Kiểm tra xem "quantity" có tồn tại và không null
            if (!request.containsKey("quantity") || request.get("quantity") == null) {
                return createErrorResponse("Quantity is required", HttpStatus.BAD_REQUEST);
            }

            int quantity;
            try {
                quantity = Integer.parseInt(request.get("quantity").toString());
            } catch (NumberFormatException e) {
                return createErrorResponse("Invalid quantity", HttpStatus.BAD_REQUEST);
            }

            return createSuccessResponse("Product added to cart successfully");
        }

        // Cập nhật số lượng sản phẩm trong giỏ hàng
        @PutMapping("/{userId}/{productId}")
        public ResponseEntity<Map<String, String>> updateCartItem(
                @PathVariable Long userId,
                @PathVariable Long productId,
                @RequestBody Map<String, Object> request) {

            if (!request.containsKey("quantity") || request.get("quantity") == null) {
                return createErrorResponse("Quantity is required", HttpStatus.BAD_REQUEST);
            }

            int quantity;
            try {
                quantity = Integer.parseInt(request.get("quantity").toString());
            } catch (NumberFormatException e) {
                return createErrorResponse("Invalid quantity", HttpStatus.BAD_REQUEST);
            }

            Optional<Cart> cartOpt = cartRepo.findByUserId(userId);
            if (cartOpt.isEmpty()) {
                return createErrorResponse("Cart not found for user", HttpStatus.NOT_FOUND);
            }

            Optional<CartItem> cartItemOpt = cartItemRepo.findByCartIdAndProductId(cartOpt.get().getCartId(), productId);
            if (cartItemOpt.isEmpty()) {
                return createErrorResponse("Product not found in cart", HttpStatus.NOT_FOUND);
            }

            CartItem cartItem = cartItemOpt.get();
            cartItem.setQuantity(quantity);
            cartItemRepo.save(cartItem);

            return createSuccessResponse("Cart item updated successfully");
        }

        // Xóa sản phẩm khỏi giỏ hàng
        @DeleteMapping("/{userId}")
        public ResponseEntity<Map<String, String>> deleteProductFromCart(
                @PathVariable Long userId,
                @RequestParam Long productId) {

            Optional<Cart> cartOpt = cartRepo.findByUserId(userId);
            if (cartOpt.isEmpty()) {
                return createErrorResponse("Cart not found for user", HttpStatus.NOT_FOUND);
            }

            Optional<CartItem> cartItemOpt = cartItemRepo.findByCartIdAndProductId(cartOpt.get().getCartId(), productId);
            if (cartItemOpt.isEmpty()) {
                return createErrorResponse("Product not found in cart", HttpStatus.NOT_FOUND);
            }

            cartItemRepo.delete(cartItemOpt.get());
            return createSuccessResponse("Product removed from cart successfully");
        }

        // Tạo ResponseEntity với thông báo thành công
        private ResponseEntity<Map<String, String>> createSuccessResponse(String message) {
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.ok(response);
        }

        // Tạo ResponseEntity với thông báo lỗi
        private ResponseEntity<Map<String, String>> createErrorResponse(String message, HttpStatus status) {
            Map<String, String> response = new HashMap<>();
            response.put("error", message);
            return ResponseEntity.status(status).body(response);
        }
    }
