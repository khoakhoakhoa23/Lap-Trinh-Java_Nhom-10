package uth.java.duan_java.services;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.CartDTO;
import uth.java.duan_java.models.dtos.CartItemDTO;
import uth.java.duan_java.models.entities.CartItem;
import uth.java.duan_java.models.entities.Product;
import uth.java.duan_java.repositories.CartItemRepo;
import uth.java.duan_java.repositories.ProductRepo;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CartItemDTO> getCartItem(){
        List<CartItem> list = cartItemRepo.findAll();
        if(list.isEmpty()){
            throw new RuntimeException("Cart is empty");
        }
        return modelMapper.map(list, new TypeToken<List<CartItemDTO>>(){}.getType());
    }

    @Override
    public CartItemDTO getCartItem(long id){
        CartItem cartItem = cartItemRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        return modelMapper.map(cartItem, CartItemDTO.class);
    }

    @Override
    public CartItemDTO createCartItem(CartItemDTO cartItem){
        CartItem cartItemEntity = modelMapper.map(cartItem, CartItem.class);

        CartDTO existingCart = cartService.getCartById(cartItemEntity.getCart().getCartId());
        if(existingCart == null){
            throw new RuntimeException("Cart not exists");
        }

        Product existingProduct = productRepo.findById(cartItemEntity.getProduct().getProductId())
                .orElseThrow(() -> new RuntimeException("Product does not exist"));

        List<CartItem> cartItems = cartItemRepo.findByCartId(existingCart.getCartId());
        for(CartItem cartItem1 : cartItems){
            if(cartItem1.getProduct().getProductId().equals(cartItemEntity.getProduct().getProductId())){
                cartItem1.setQuantity(cartItem.getQuantity() + cartItem1.getQuantity());
                return modelMapper.map(cartItemRepo.save(cartItem1), CartItemDTO.class);
            }
        }
        cartItemEntity = cartItemRepo.save(cartItemEntity);

        return modelMapper.map(cartItemEntity, CartItemDTO.class);
    }



    @Override
    public CartItemDTO updateCartItem(CartItemDTO cartItemDTO, long id) {
        CartItem existingCartItem = cartItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Cập nhật thông tin từ DTO
        existingCartItem.setQuantity(cartItemDTO.getQuantity());
        existingCartItem.setProduct(modelMapper.map(cartItemDTO.getProduct(), existingCartItem.getProduct().getClass()));

        CartItem updatedCartItem = cartItemRepo.save(existingCartItem);
        return modelMapper.map(updatedCartItem, CartItemDTO.class);
    }

    @Override
    public boolean deleteCartItem(long id){
        CartItem existingCartItem = cartItemRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        cartItemRepo.delete(existingCartItem);
        return true;
    }

    @Override
    public boolean removeAllCartItemByCartID(long cartId) {
        List<CartItem> cartItems = cartItemRepo.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("No items found for the specified cart ID");
        }
        for (CartItem cartItem : cartItems) {
            if(!deleteCartItem(cartItem.getCart().getCartId())){
               throw new RuntimeException("Cart not deleted");
            }
        }
        return true;
    }


}

