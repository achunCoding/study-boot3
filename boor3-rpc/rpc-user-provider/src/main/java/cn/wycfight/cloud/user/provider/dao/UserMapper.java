package cn.wycfight.cloud.user.provider.dao;

import cn.wycfight.cloud.user.provider.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2023/8/15 17:15
 * @Created by achun
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
