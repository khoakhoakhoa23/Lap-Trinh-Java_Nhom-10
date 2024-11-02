package uth.java.duan_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uth.java.duan_java.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    // Tìm sản phẩm theo tên
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findByName(@Param("name") String name);

    // Tìm danh sách sản phẩm theo danh mục
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategoryId(@Param("category") String categoryId);
}
