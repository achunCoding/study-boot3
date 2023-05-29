package cn.wycfight.cloud.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.wycfight.cloud.example.dto.OrderDTO;
import cn.wycfight.cloud.example.entity.Order;
import cn.wycfight.cloud.example.entity.OrderItem;
import cn.wycfight.cloud.example.mapper.OrderMapper;
import cn.wycfight.cloud.example.service.OrderItemService;
import cn.wycfight.cloud.example.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private OrderItemService orderItemService;

    @Override
    public boolean saveOrderInfo(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtil.copyProperties(orderDTO, order);
        save(order);
        List<OrderItem> orderItems = orderDTO.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getOrderId());
        }
        return orderItemService.saveBatch(orderItems);
    }

    @Override
    public List<Order> selectOrderList() {
        Long id = 1663078580629209089L;
        return baseMapper.selectList(new QueryWrapper<Order>().eq("order_id", id));
    }
}
