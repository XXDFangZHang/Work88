package com.cjh.service.user;

import com.cjh.bean.Users;
import com.cjh.dao.user.UserDao;
import com.cjh.dao.user.UserDaoImpl;
import com.cjh.util.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 在不改变dao层代码的前提下，做一些业务逻辑！
 * 增强处理==》增加系统级业务
 */
public class UserServiceImpl implements UserService {

    //耦合 加上 静态代理
    private UserDao userDao=new UserDaoImpl();

    @Override
    public int add(Users users) {
        return  userDao.add(users);
    }

    @Override
    public int deleteByCondition(Serializable id) {
        return 0;
    }

    @Override
    public int update(Users users) {
        return 0;
    }

    @Override
    public Users findByCondition(Serializable id) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public int findRownum() {
        return 0;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        return null;
    }
}
