package cn.wycfight.cloud.example.dto;

import cn.wycfight.cloud.example.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private List<OrderItem> orderItems;

    private Long orderId;

    private String orderNo;

    private Long userId;

    private String createName;

    private BigDecimal price;
}
