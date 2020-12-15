/************************
     Author: 丁宇翔
     Date: 2020.12.7
 ************************/
package service;

import dao.impl.UserDaoImpl;
import vo.User;

import java.util.List;

public class UserService
{
    private UserDaoImpl userDao = new UserDaoImpl();

    public void add(User user)
    {
        userDao.add(user);
    }

    public void delete(User user)
    {
        userDao.delete(user);
    }

    public List<User> findAll()
    {
        return userDao.findAll();
    }

    /* 通过id查找用户 */
    public User getUserById(int id)
    {
        return userDao.getUserById(id);
    }

    /* 通过性别查找用户 */
    public List<User> getUserBySex(String sex)
    {
        return userDao.getUserBySex(sex);
    }

    /* 根据用户名查找用户 */
    public List<User> getUserByName(String name)
    {
        return userDao.getUserByName(name);
    }
}
