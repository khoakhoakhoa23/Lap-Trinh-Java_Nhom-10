package uth.java.duan_java.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity //nhan biet day la entity
@Table(name = "users") // tao table vs name = "..."
@Data // getter and setter
@NoArgsConstructor // ham khoi vs rong
@AllArgsConstructor // hom khoi tao vs tat ca thuộc tính
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto tạo ID voi kieu du lieu "Long"
    private Long userId;

    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDate createdDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;
}

