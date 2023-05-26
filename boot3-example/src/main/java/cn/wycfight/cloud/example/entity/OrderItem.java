package cn.wycfight.cloud.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName(value = "order_item")
public class OrderItem implements Serializable {

    @TableId(type = IdType.ASSIGN_ID, value = "item_id")
    private Long itemId;

    @TableField(value = "order_id")
    private Long orderId;

    @TableField(value = "order_no")
    private String orderNo;
    @TableField(value = "item_name")
    private String itemName;

    @TableField(value = "price")
    private BigDecimal price;


}
