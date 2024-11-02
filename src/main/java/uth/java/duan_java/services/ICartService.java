package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.CartDTO;
import uth.java.duan_java.models.dtos.UserDTO;

import java.util.List;

public interface ICartService {
    List<CartDTO> getCarts();

    CartDTO getCartById(long id);

    CartDTO createCart(UserDTO cartDTO);

    CartDTO updateCart(CartDTO cartDTO, long id);

    boolean deleteCart(long id);

}