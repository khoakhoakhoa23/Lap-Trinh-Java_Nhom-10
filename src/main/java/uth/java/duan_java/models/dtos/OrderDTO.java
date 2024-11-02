package uth.java.duan_java.models.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;

    private UserDTO user;

    private LocalDate orderDate;
    private String status;
    private double totalPrice;

    @JsonManagedReference
    private List<OrderDetailDTO> orderDetails;
}
