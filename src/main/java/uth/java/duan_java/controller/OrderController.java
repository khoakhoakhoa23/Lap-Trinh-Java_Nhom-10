package uth.java.duan_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uth.java.duan_java.models.entities.Order;
import uth.java.duan_java.repositories.OrderRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    // 1. Lấy danh sách tất cả đơn hàng
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // 2. Lấy đơn hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepo.findById((long) Math.toIntExact(id));
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 3. Tạo đơn hàng mới
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderRepo.save(order);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    // 4. Cập nhật đơn hàng
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> existingOrder = orderRepo.findById((long) Math.toIntExact(id));
        if (existingOrder.isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        Order order = existingOrder.get();
        order.setStatus(updatedOrder.getStatus()); // Cập nhật trạng thái hoặc thuộc tính khác nếu cần
        orderRepo.save(order);
        return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
    }

    // 5. Xóa đơn hàng
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeOrder(@PathVariable Long id) {
        Optional<Order> existingOrder = orderRepo.findById((long) Math.toIntExact(id));
        if (existingOrder.isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        orderRepo.delete(existingOrder.get());
        return new ResponseEntity<>("Order removed successfully", HttpStatus.OK);
    }
}
