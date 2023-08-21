package cn.wycfight.cloud.user.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Classname UserEntity
 * @Description TODO
 * @Date 2023/8/15 17:14
 * @Created by achun
 */
@TableName("t_user")
@Data
@ToString
public class UserEntity {

    @TableId(type = IdType.INPUT)
    private Long userId;
    private String nickName;
    private String trueName;
    private String avatar;
    private Integer sex;
    private Integer workCity;
    private Integer bornCity;
    private Date bornTime;
    private Date createTime;
    private Date updateTime;
}
