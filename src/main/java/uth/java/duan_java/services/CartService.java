package uth.java.duan_java.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.CartDTO;
import uth.java.duan_java.models.dtos.UserDTO;
import uth.java.duan_java.models.entities.Cart;
import uth.java.duan_java.models.entities.User;
import uth.java.duan_java.repositories.CartRepo;
import uth.java.duan_java.repositories.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Lấy tất cả giỏ hàng
    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepo.findAll();
        if (carts.isEmpty()) {
            throw new RuntimeException("No carts found");
        }
        return modelMapper.map(carts, new TypeToken<List<CartDTO>>() {}.getType());
    }

    // Lấy giỏ hàng của người dùng theo userId
    @Override
    public CartDTO getCartById(long userId) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user with ID: " + userId));
        return modelMapper.map(cart, CartDTO.class);
    }

    // Tạo giỏ hàng cho người dùng nếu chưa có
    @Override
    public CartDTO createCart(UserDTO userDTO) {
        User user = userRepo.findByEmail(userDTO.getEmail());
        if (user == null) {
            throw new RuntimeException("User does not exist with email: " + userDTO.getEmail());
        }

        Optional<Cart> existingCart = cartRepo.findByUserId(user.getUserId());
        if (existingCart.isPresent()) {
            throw new RuntimeException("Cart already exists for user with ID: " + user.getUserId());
        }

        Cart cart = new Cart();
        cart.setUser(user);
        Cart newCart = cartRepo.save(cart);
        return modelMapper.map(newCart, CartDTO.class);
    }

    // Cập nhật giỏ hàng theo cartId
    @Override
    public CartDTO updateCart(CartDTO cartDTO, long cartId) {
        Cart existingCart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cannot update, cart not found with ID: " + cartId));
        modelMapper.map(cartDTO, existingCart);
        cartRepo.save(existingCart);
        return modelMapper.map(existingCart, CartDTO.class);
    }

    // Xóa giỏ hàng theo cartId
    @Override
    public boolean deleteCart(long cartId) {
        Cart existingCart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + cartId));
        cartRepo.delete(existingCart);
        return true;
    }

    // Kiểm tra giỏ hàng có chứa sản phẩm hay không dựa trên cartId và productId
    public boolean doesCartContainProduct(Long cartId, Long productId) {
        return cartRepo.existsByCartIdAndProductId(cartId, productId);
    }


}
