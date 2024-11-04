package uth.java.duan_java.services;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uth.java.duan_java.models.dtos.OrderDetailDTO;
import uth.java.duan_java.models.entities.OrderDetail;
import uth.java.duan_java.repositories.OrderDetailRepo;


import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<OrderDetailDTO> getOrderDetails(){
        List<OrderDetail> list = orderDetailRepo.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("No order details found");
        }
        return modelMapper.map(list, new TypeToken<List<OrderDetailDTO>>(){}.getType());
    }
    @Override
    public OrderDetailDTO getOrderDetailById(long id){
        OrderDetail orderDetail = orderDetailRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("No order detail found"));
        return modelMapper.map(orderDetail, OrderDetailDTO.class);
    }

    public OrderDetailDTO createOrderDetail(OrderDetailDTO dto){
        OrderDetail orderDetail = modelMapper.map(dto, OrderDetail.class);
        orderDetail = orderDetailRepo.save(orderDetail);
        return modelMapper.map(orderDetail, OrderDetailDTO.class);
    }

    public OrderDetailDTO updateOrderDetail(OrderDetailDTO dto, long id){
        OrderDetail existingOrderDetail = orderDetailRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("No order detail found"));
        existingOrderDetail.setQuantity(dto.getQuantity());
        existingOrderDetail.setPrice(dto.getPrice());
        existingOrderDetail = orderDetailRepo.save(existingOrderDetail);
        return modelMapper.map(existingOrderDetail, OrderDetailDTO.class);

    }

    public boolean deleteOrderDetail(long id){
        OrderDetail existingOrderDetail = orderDetailRepo.findById((long) id)
                .orElseThrow(()->new RuntimeException("No order detail found"));
        orderDetailRepo.delete(existingOrderDetail);
        return true;
    }
}
