package cn.wycfight.cloud.user.provider.rpc;

import cn.wycfight.cloud.common.interfaces.ConvertBeanUtils;
import cn.wycfight.cloud.user.dto.UserDTO;
import cn.wycfight.cloud.user.interfaces.IUserRpc;
import cn.wycfight.cloud.user.provider.entity.UserEntity;
import cn.wycfight.cloud.user.provider.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname UserRpcImpl
 * @Description TODO
 * @Date 2023/8/8 16:18
 * @Created by achun
 */
@DubboService
public class UserRpcImpl implements IUserRpc {


    @Autowired
    private IUserService userService;

    @Override
    public String test() {
        System.out.println("this is dubbo service");
        return "success";
    }

    @Override
    public UserDTO getUserById(Long userId) {
        UserEntity userEntity = userService.getById(userId);
        return ConvertBeanUtils.convert(userEntity, UserDTO.class);
    }
}
