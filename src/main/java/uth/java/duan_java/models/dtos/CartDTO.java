package uth.java.duan_java.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDTO {
    private Long cartId;

    @JsonManagedReference
    private List<CartItemDTO> cartItems;

    @JsonIgnore
    private UserDTO user;

}
