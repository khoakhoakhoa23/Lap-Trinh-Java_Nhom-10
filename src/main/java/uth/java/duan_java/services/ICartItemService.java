package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.CartItemDTO;
import java.util.List;

public interface ICartItemService {
    List<CartItemDTO> getCartItem();

    CartItemDTO getCartItem(long id);

    CartItemDTO createCartItem(CartItemDTO cartItem);

    CartItemDTO updateCartItem(CartItemDTO cartItem, long id);

    boolean deleteCartItem(long id);

    boolean removeAllCartItemByCartID(long cartId);
}
