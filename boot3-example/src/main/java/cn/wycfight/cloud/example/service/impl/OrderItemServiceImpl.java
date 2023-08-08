package cn.wycfight.cloud.example.service.impl;

import cn.wycfight.cloud.example.entity.OrderItem;
import cn.wycfight.cloud.example.mapper.OrderItemMapper;
import cn.wycfight.cloud.example.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void howToTransaction(List<OrderItem> orderItemList) {
        saveBatch(orderItemList);
    }
}
