package dao;

import vo.User;

import java.sql.Date;
import java.util.List;

public interface UserDao
{
    public void add(User user);

    public void delete(User user);

    public List<User> findAll();

    /* 通过id查找 */
    public User getUserById(int id);

    /* 通过性别查找 */
    public List<User> getUserBySex(String sex);

    /* 通过用户名查找 */
    public List<User> getUserByName(String name);

    public List<User> getUserByBirthday(Date date);
}
