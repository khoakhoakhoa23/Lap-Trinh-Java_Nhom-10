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
public class UserDTO {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDate createdDate;


    @JsonIgnore
    private List<OrderDTO> orders;

    @JsonBackReference(value = "rv-users")
    private List<ReviewDTO> reviews;

    @JsonIgnore
    private CartDTO cart;
}
