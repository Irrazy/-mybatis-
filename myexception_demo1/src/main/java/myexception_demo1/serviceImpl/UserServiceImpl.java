package myexception_demo1.serviceImpl;

import myexception_demo1.mapper.UserMapper;
import myexception_demo1.pojo.User;
import myexception_demo1.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: tk-generator-lombok
 * @description: UserServiceImpl
 * @author: 作者
 * @create: 2021-12-23 14:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    //查一个ById
    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }



    //增加一个

    @Override
    public int insertSelective(User user) {
        //用insertSelective不会给没有赋值的字段插入null值，有些字段有默认值就要用这个方法
        return userMapper.insertSelective(user);
    }

    //实体更新
    @Override
    public int updateByPrimaryKey(User user) {
        //根据主键字段准确地修改某一条记录。---update table set 所有字段
        return userMapper.updateByPrimaryKey(user);
    }

    //删除
    //ById
    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    //分页
  /*
     @Override
     public PageInfo<User> getUsersByPage(Integer pageNum,Integer pageSize) {
        //DESC表示倒叙排列,这个引号里可填可不填
        //PageHelper.startPage(pageNum,pageSize,"id DESC");
        PageHelper.startPage(pageNum,pageSize,"id ASC");
        List<User> users = userMapper.selectAll();
        return new PageInfo<>(users);
    }
   */



}

