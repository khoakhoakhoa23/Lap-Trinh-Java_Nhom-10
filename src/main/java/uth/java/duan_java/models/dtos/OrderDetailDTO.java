package uth.java.duan_java.models.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private Long orderDetailId;

    @JsonBackReference
    private OrderDTO order;

    private ProductDTO product;

    private int quantity;
    private double price;
}
