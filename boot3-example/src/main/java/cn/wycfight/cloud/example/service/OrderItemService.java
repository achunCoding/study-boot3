package cn.wycfight.cloud.example.service;

import cn.wycfight.cloud.example.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderItemService extends IService<OrderItem> {


    void howToTransaction(List<OrderItem> orderItemList);
}
