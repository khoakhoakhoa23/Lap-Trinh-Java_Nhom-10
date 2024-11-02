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

    @Override
    public List<CartDTO> getCarts() {
        List<Cart> carts = cartRepo.findAll();
        if (carts.isEmpty()) {
            throw new RuntimeException("No cart found");
        }
        return modelMapper.map(carts, new TypeToken<List<CartDTO>>() {}.getType());
    }

    @Override
    public CartDTO getCartById(long id){
        Cart cart = cartRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return modelMapper.map(cart, CartDTO.class);

    }

    @Override
    public CartDTO createCart(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        User extinguUser = userRepo.findByEmail(userDTO.getEmail());
        if (extinguUser == null) {
            throw new RuntimeException("User not exists");
        }
        Cart cart = new Cart();
        cart.setUser(user);
        Cart nwCart = cartRepo.save(cart);
        return modelMapper.map(nwCart, CartDTO.class);
    }

    @Override
    public CartDTO updateCart(CartDTO cartDTO, long id) {
        Cart existingCart = cartRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Cannot update, cart not found"));
        modelMapper.map(cartDTO, existingCart);
        cartRepo.save(existingCart);
        return modelMapper.map(existingCart, CartDTO.class);
    }
    @Override
    public boolean deleteCart(long id){
        Cart existingCartItem = cartRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        cartRepo.delete(existingCartItem);
        return true;
    }

    // Kiểm tra giỏ hàng có chứa sản phẩm hay không
    public boolean doesCartContainProduct(Long productId) {
        Optional<Cart> cartContainingProduct = cartRepo.findByProductId(productId);
        return cartContainingProduct.isPresent();
    }

}
