package uth.java.duan_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uth.java.duan_java.models.entities.OrderDetail;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    // Tìm danh sách OrderDetail theo orderId
    @Query("SELECT od FROM OrderDetail od WHERE od.order.orderId = :orderId")
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);
}
