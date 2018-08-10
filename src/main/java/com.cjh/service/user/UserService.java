package com.cjh.service.user;

import com.cjh.bean.Users;
import com.cjh.service.IBaseService;

public interface UserService extends IBaseService<Users> {
    String validateName(String userName);

    Users login(String userName, String password);
}
