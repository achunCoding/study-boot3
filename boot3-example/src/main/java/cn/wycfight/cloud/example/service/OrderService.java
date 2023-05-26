package cn.wycfight.cloud.example.service;

import cn.wycfight.cloud.example.dto.OrderDTO;
import cn.wycfight.cloud.example.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {

    /**
     * 创建订单信息
     * @param orderDTO 订单实体
     * @return true : 创建成功 false : 创建失败
     */
    boolean saveOrderInfo(OrderDTO orderDTO);
}
