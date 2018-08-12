package com.cjh.dao.user;
import com.cjh.bean.Users;
import com.cjh.util.BaseDao;
import com.cjh.util.PageUtil;
import com.cjh.util.ResultSetUtil;

import java.io.Serializable;
import java.util.List;
import java.sql.SQLException;
public class UserDaoImpl extends BaseDao implements UserDao{
    /**
     * 注册功能
     */
    @Override
    public int add(Users users) {
        String sql = "INSERT INTO news_user(userName,`password`,email,userType) VALUES(?,?,?,?)";
        Object[] params = {users.getUserName(), users.getPassword(), users.getEmail(), users.getUserType()};
        return executeUpdate(sql, params);
    }
    /**
     * 验证用户名是否存在的操作/密码是否正确
     */
    @Override
    public String validateName(String userName) {
        String sql = "SELECT password FROM news_user WHERE userName=?";
        rs = executeQuery(sql, userName);
        String password = null;
        try { //获取密码
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 登录
     */
    @Override
    public Users login(String userName, String password) {
        String sql="SELECT id as users_id,userName,PASSWORD,email,userType FROM news_user where userName=? and password=?";
        Object [] params={userName,password};
        rs=executeQuery(sql,params);
        Users users= ResultSetUtil.eachOne(rs,Users.class);
        return users;
    }
    /**
     * 查询总记录数
     */
    @Override
    public int findRownum() {
        String sql="SELECT COUNT(id) as count FROM news_user";
        rs=executeQuery(sql);
        int count=0;
        try {
            if (rs.next()){
                count= rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public int deleteByCondition(Serializable id) {
        String sql="delete from news_user where id=?";
        int num= executeUpdate(sql,id);
        return num;
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
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        String sql="SELECT id as users_id,userName,PASSWORD,email,userType FROM news_user limit ?,?";
        Object[] p={(util.getPageIndex()-1)*util.getPageSize(),util.getPageSize()};
        rs= executeQuery(sql,p);
        List<Users>  list= ResultSetUtil.eachList(rs,Users.class);
        return list;
    }
}
