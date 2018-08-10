package com.cjh.dao.user;

import com.cjh.bean.Users;
import com.cjh.dao.IBaseDao;

/**
 * 书写自己特有的功能
 */
public interface UserDao extends IBaseDao<Users> {

    /**
     * 验证用户名的操作
     */
    String validateName(String userName);

    Users login(String userName, String password);

}
