/************************
     Author: 丁宇翔
     Date: 2020.12.2
 ************************/
package dao.impl;

import dao.JDBCUtil;
import dao.UserDao;
import vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao
{
    @Override
    public void add(User user)
    {
        String sql = "INSERT INTO `user` VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        JDBCUtil.executeSql(sql, "int", String.valueOf(user.getId()), "String", user.getName(), "String", user.getPassword(), "Date", user.getBirthday().toString(), "String", user.getSex(), "String", user.getImage(), "String", user.isMessage(), "String", user.isRequest());
    }

    @Override
    public void delete(User user)
    {
        String sql = "DELETE FROM `user` WHERE `id` = ?";
        JDBCUtil.executeSql(sql, "int", String.valueOf(user.getId()));
    }

    @Override
    public List<User> findAll()
    {
        String sql = "select * from `user`";
        return JDBCUtil.getListBySql(sql, "User");
    }

    /* 根据id查找用户 */
    @Override
    public User getUserById(int id)
    {
        String sql = "select * from `user` where `id` = ?";
        return (User) JDBCUtil.getListBySql(sql, "int", String.valueOf(id), "User").get(0);
    }

    /* 根据性别查找 */
    @Override
    public List<User> getUserBySex(String sex)
    {
        String sql = "select * from `user` where `sex` = ?";

        return JDBCUtil.getListBySql(sql, "String", sex, "User");
    }

    /* 根据用户名查找 */
    @Override
    public List<User> getUserByName(String name)
    {
        String sql = "SELECT * FROM `user` WHERE `name` LIKE ?";
        name = "%" + name + "%";
        return JDBCUtil.getListBySql(sql, "String", name, "User");
    }

    /* 根据生日查找 */
    @Override
    public List<User> getUserByBirthday(Date date)
    {
        String sql = "select * from `user` where `birthday` = ?";
        return JDBCUtil.getListBySql(sql, "Date", date.toString(), "User");
    }
}
