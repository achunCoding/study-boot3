package cn.wycfight.cloud.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by achun
 * @Classname UserDTO
 * @Description TODO
 * @Date 2023/8/15 17:44
 */

@Data
public class UserDTO implements Serializable {

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
