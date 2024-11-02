package uth.java.duan_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uth.java.duan_java.models.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    // Tìm người dùng theo tên đăng nhập
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    // Tìm người dùng theo email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
