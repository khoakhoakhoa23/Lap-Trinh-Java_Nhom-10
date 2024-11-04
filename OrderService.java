package uth.java.duan_java.services;



import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.OrderDTO;
import uth.java.duan_java.models.entities.Order;
import uth.java.duan_java.repositories.OrderRepo;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderDTO> getOrders(){
        List<Order> list = orderRepo.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("No orders found");
        }
        return modelMapper.map(list, new TypeToken<List<OrderDTO>>(){}.getType());
    }
    @Override
    public OrderDTO getOrderById(long id){
        Order order = orderRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderDTO.class);
    }
    @Override
    public OrderDTO createOrder(OrderDTO order){
        Order orderToSave = modelMapper.map(order, Order.class);
        orderToSave = orderRepo.save(orderToSave);
        return modelMapper.map(orderToSave, OrderDTO.class);
    }
    @Override
    public OrderDTO updateOrder(OrderDTO order, long id) {
        Order existingOrder = orderRepo.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setStatus(existingOrder.getStatus());
        existingOrder.setOrderDate(existingOrder.getOrderDate());
        existingOrder.setTotalPrice(existingOrder.getTotalPrice());
        modelMapper.map(order, existingOrder);
        existingOrder = orderRepo.save(existingOrder);
        return modelMapper.map(existingOrder, OrderDTO.class);
    }
    @Override
    public boolean deleteOrder(long id){
        Order existingOrder = orderRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("Order not found"));
        orderRepo.delete(existingOrder);
        return true;

    }
}
