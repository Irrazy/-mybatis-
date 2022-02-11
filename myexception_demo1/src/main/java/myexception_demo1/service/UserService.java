package myexception_demo1.service;


import myexception_demo1.pojo.User;

import java.util.List;

public interface UserService {
    //查
    List<User> selectAll();

    User findById(Long id);

    //增
    //int insert(User user);
    int insertSelective(User user);
    //批量插入和批量查询及批量插入都不能用，因为 dao 层的 mapper 接口还要继承 MySqlMapper

    int updateByPrimaryKey(User user);

    //删--先删再调用查所有
    int delete(Long id);


    //分页--利用分页插件
    //pageNum表示当前页，pageSize表示每页的数量
   // PageInfo<User> getUsersByPage(Integer pageNum, Integer pageSize);

}
