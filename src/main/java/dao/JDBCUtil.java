/************************
     Author: 丁宇翔
     Date: 2020.12.2
************************/
package dao;

import vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil
{
    private static String DB_URL = "jdbc:sqlite:a.db";
    private static String DB_DRIVER = "org.sqlite.JDBC";
    private static Connection connection = null;

    /* 连接数据库 */
    public static Connection getConnection()
    {
        try
        {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("连接数据库异常");
            e.printStackTrace();
        }
        return connection;
    }

    /* 关闭连接 */
    public static void closeConnection(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static List<User> getListBySql(String sql, String value, String type)
    {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            /* 查询类型为String */
            if (type.equals("String"))
            {
                preparedStatement.setString(1, value);
            }
            /* 查询类型为Int */
            else if (type.equals("int"))
            {
                preparedStatement.setInt(1, Integer.getInteger(value).intValue());
            }
            /* 查询类型为Date */
            else if (type.equals("Date"))
            {
                preparedStatement.setDate(1, Date.valueOf(value));
            }
            /* 无查询类型 */
            else if (type == null)
            {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }

            /* 如果无查询类型，则用preparedStatement查询 */
            if (statement == null)
            {
                resultSet = preparedStatement.executeQuery(sql);
            }

            while (resultSet.next())
            {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setSex(resultSet.getString("sex"));
                user.setImage(resultSet.getString("image"));
                user.setMessage(resultSet.getBoolean("message"));
                user.setRequest(resultSet.getBoolean("request"));
                list.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCUtil.closeConnection(connection);
        }
        return list;
    }
}
