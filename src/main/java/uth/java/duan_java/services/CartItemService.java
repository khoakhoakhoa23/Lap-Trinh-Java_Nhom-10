package uth.java.duan_java.services;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.CartItemDTO;
import uth.java.duan_java.models.entities.CartItem;
import uth.java.duan_java.repositories.CartItemRepo;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

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
        cartItemEntity = cartItemRepo.save(cartItemEntity);
        return modelMapper.map(cartItemEntity, CartItemDTO.class);
    }


    @Override
    public CartItemDTO updateCartItem(CartItemDTO cartItem, long id){
        CartItem existingCartItem = cartItemRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        existingCartItem.setQuantity(existingCartItem.getQuantity());
        existingCartItem.setProduct(existingCartItem.getProduct());
        cartItemRepo.save(existingCartItem);
        return modelMapper.map(existingCartItem, CartItemDTO.class);
    }

    @Override
    public boolean deleteCartItem(long id){
        CartItem existingCartItem = cartItemRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("Cart not found"));
        cartItemRepo.delete(existingCartItem);
        return true;
    }
}
