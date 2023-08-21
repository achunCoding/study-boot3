package cn.wycfight.cloud.api.controller;

import cn.wycfight.cloud.user.interfaces.IUserRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author achun
 * @Date 2023/8/8 22:41
 * @Version 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @DubboReference
    private IUserRpc iUserRpc;


    @RequestMapping("/dubbo")
    public String dubbo() {
        System.out.println(iUserRpc.getUserById(1L));
        return "success";
    }
}
