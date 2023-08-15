package cn.wycfight.cloud.user.provider.service.impl;

import cn.wycfight.cloud.user.provider.dao.UserMapper;
import cn.wycfight.cloud.user.provider.entity.UserEntity;
import cn.wycfight.cloud.user.provider.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author by achun
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2023/8/15 17:18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
