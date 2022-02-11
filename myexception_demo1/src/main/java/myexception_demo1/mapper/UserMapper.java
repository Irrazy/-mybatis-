package myexception_demo1.mapper;

import myexception_demo1.common.BaseMapper;
import myexception_demo1.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}