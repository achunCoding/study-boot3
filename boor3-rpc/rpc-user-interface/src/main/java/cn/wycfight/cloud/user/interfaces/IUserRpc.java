package cn.wycfight.cloud.user.interfaces;

import cn.wycfight.cloud.user.dto.UserDTO;

/**
 * @Classname IUserRpc
 * @Description 用户中台RPC提供者
 * @Date 2023/8/8 16:17
 * @Created by achun
 */
public interface IUserRpc {

    String test();

    /**
     * 通过用户Id查询用户信息
     * @param userId 用户Id
     * @return
     */
    UserDTO getUserById(Long userId);
}
