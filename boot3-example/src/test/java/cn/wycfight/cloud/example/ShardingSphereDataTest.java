package cn.wycfight.cloud.example;

import cn.wycfight.cloud.example.dto.OrderDTO;
import cn.wycfight.cloud.example.entity.Order;
import cn.wycfight.cloud.example.entity.OrderItem;
import cn.wycfight.cloud.example.service.OrderItemService;
import cn.wycfight.cloud.example.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ShardingSphereDataTest {


    @Autowired
    private OrderService orderService;


    @Autowired
    private OrderItemService orderItemService;

    @Test
    public void saveOrderInfoTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setPrice(new BigDecimal("100"));
        orderDTO.setCreateName("哈哈1111");
        orderDTO.setOrderNo("202300000014000");
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setItemName("物品详情1");
        orderItem1.setPrice(new BigDecimal("50"));
        orderItem1.setOrderNo("202300000014000001");
        orderItems.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItemName("物品详情2");
        orderItem2.setPrice(new BigDecimal("50"));
        orderItem2.setOrderNo("202300000014000002");
        orderItems.add(orderItem2);
        orderDTO.setOrderItems(orderItems);
        orderService.saveOrderInfo(orderDTO);

    }


    @Test
    public void selectOrderListTest() {
        List<Order> orders = orderService.selectOrderList();
    }


    @Test
    public void howToOrderList() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setPrice(new BigDecimal("100"));
        orderDTO.setCreateName("哈哈1111");
        orderDTO.setOrderNo("202300000014000");
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setItemName("物品详情1");
        orderItem1.setPrice(new BigDecimal("50"));
        orderItem1.setOrderNo("202300000014000001");
        orderItem1.setOrderId(1l);
        orderItems.add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItemName("物品详情2");
        orderItem2.setOrderId(1l);
        orderItem2.setPrice(new BigDecimal("50"));
        orderItem2.setOrderNo("202300000014000002");
        orderItems.add(orderItem2);
        orderDTO.setOrderItems(orderItems);
        orderItemService.howToTransaction(orderItems);
    }

    @Test
    public void testMonth() {
        String s = new BigDecimal("528").divide(new BigDecimal("70"), 2, RoundingMode.UP).toPlainString();
        System.out.println(s);
    }
}
