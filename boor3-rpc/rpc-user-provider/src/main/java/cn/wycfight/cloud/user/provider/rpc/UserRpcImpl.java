package cn.wycfight.cloud.user.provider.rpc;

import cn.wycfight.cloud.user.interfaces.IUserRpc;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Classname UserRpcImpl
 * @Description TODO
 * @Date 2023/8/8 16:18
 * @Created by achun
 */
@DubboService
public class UserRpcImpl implements IUserRpc {
    @Override
    public String test() {
        System.out.println("this is dubbo service");
        return "success";
    }
}
