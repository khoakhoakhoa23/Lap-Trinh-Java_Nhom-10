package uth.java.duan_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.entities.OrderDetail;
import uth.java.duan_java.repositories.OrderDetailRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    // 1. Lấy danh sách tất cả chi tiết đơn hàng theo orderId
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails(@PathVariable Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepo.findByOrderId(orderId);
        if (orderDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    // 2. Thêm chi tiết đơn hàng mới
    @PostMapping("/add")
    public ResponseEntity<String> addOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailRepo.save(orderDetail);
        return new ResponseEntity<>("Order detail added successfully", HttpStatus.CREATED);
    }

    // 3. Cập nhật chi tiết đơn hàng
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail updatedOrderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepo.findById((long) Math.toIntExact(id));
        if (existingOrderDetail.isEmpty()) {
            return new ResponseEntity<>("Order detail not found", HttpStatus.NOT_FOUND);
        }

        OrderDetail orderDetail = existingOrderDetail.get();
        orderDetail.setQuantity(updatedOrderDetail.getQuantity()); // Cập nhật số lượng hoặc thuộc tính khác nếu cần
        orderDetailRepo.save(orderDetail);
        return new ResponseEntity<>("Order detail updated successfully", HttpStatus.OK);
    }

    // 4. Xóa chi tiết đơn hàng
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeOrderDetail(@PathVariable Long id) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepo.findById((long) Math.toIntExact(id));
        if (existingOrderDetail.isEmpty()) {
            return new ResponseEntity<>("Order detail not found", HttpStatus.NOT_FOUND);
        }

        orderDetailRepo.delete(existingOrderDetail.get());
        return new ResponseEntity<>("Order detail removed successfully", HttpStatus.OK);
    }
}
