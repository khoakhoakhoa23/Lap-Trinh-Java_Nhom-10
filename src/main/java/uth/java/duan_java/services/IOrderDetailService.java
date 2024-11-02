package uth.java.duan_java.services;

import uth.java.duan_java.models.dtos.OrderDetailDTO;
import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailDTO> getOrderDetails();

    OrderDetailDTO getOrderDetailById(long id);

    OrderDetailDTO createOrderDetail(OrderDetailDTO dto);

    OrderDetailDTO updateOrderDetail(OrderDetailDTO dto, long id);

    boolean deleteOrderDetail(long id);

}
