package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.OrderDTO;
import uth.java.duan_java.models.dtos.OrderDetailDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getOrders();

    OrderDTO getOrderById(long id);

    OrderDTO createOrder(OrderDTO order);

    OrderDTO updateOrder(OrderDTO order, long id);

    boolean deleteOrder(long id);
}