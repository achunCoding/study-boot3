package cn.wycfight.cloud.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息
 */
@Data
@TableName("order")
public class Order implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long orderId;

    @TableField(value = "order_no")
    private String orderNo;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "create_name")
    private String createName;

    @TableField(value = "price")
    private BigDecimal price;
}
