package uth.java.duan_java.models.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
    private String image;
    private LocalDate createdDate;

    @JsonBackReference(value = "details-products")
    private List<OrderDetailDTO> orderDetails;

    @JsonBackReference(value = "cartItems-product")
    private List<CartItemDTO> cartItems;

    @JsonIgnore
    private List<ReviewDTO> reviews;
}
